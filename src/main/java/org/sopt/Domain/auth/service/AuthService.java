package org.sopt.Domain.auth.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.sopt.Domain.auth.exception.AuthInvalidToken;
import org.sopt.Domain.auth.exception.AuthTokenxception;
import org.sopt.Domain.auth.exception.AuthUserException;
import org.sopt.Domain.user.entity.User;
import org.sopt.Domain.auth.RefreshToken;
import org.sopt.Domain.auth.repository.RefreshTokenRepository;
import org.sopt.Domain.user.repository.UserRepository;
import org.sopt.Domain.auth.dto.response.TokenResponse;
import org.sopt.Domain.user.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.jwt.refresh-token-expires-in-seconds:1209600}")
    private long refreshTokenExpiresInSeconds;

    public UserResponse loginWithCredentials(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return UserResponse.from(user);
    }


    // 로그인: 두 토큰 동시 발급
    @Transactional
    public TokenResponse login(String email, String password) {
        // 이메일/비밀번호 검증
        // Access Token + Refresh Token 발급
        // Refresh Token DB 저장
        UserResponse user = loginWithCredentials(email, password);

        String accessToken = jwtService.generateAccessToken(user.id(), user.email());
        String refreshToken = jwtService.generateRefreshToken(user.id());

        // 기존 Refresh Token 삭제 후 새로 저장
        refreshTokenRepository.deleteByUserId(user.id());
        refreshTokenRepository.save(
                RefreshToken.of(user.id(), refreshToken, refreshTokenExpiresInSeconds)
        );

        return TokenResponse.of(accessToken, refreshToken);
    }

    // 재발급: Refresh Token으로 Access Token 재발급
    public TokenResponse reissue(String refreshTokenValue) {
        // DB에서 Refresh Token 조회
        RefreshToken storedToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(AuthTokenxception::new);

        // 만료 여부 확인
        Long userId = storedToken.getUserId();
        try{
            userId = jwtService.verifyAndGetMemberId(refreshTokenValue); // JWT 서명 검증
        } catch (JWTVerificationException | IllegalArgumentException e){
            throw new AuthInvalidToken();
        }

        // 새 Access Token + 새 Refresh Token 발급 (Rotate)
        User user = userRepository.findById(userId)
                .orElseThrow(AuthUserException::new);

        String newAccessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
        String newRefreshToken = jwtService.generateRefreshToken(user.getId());



        return TokenResponse.of(newAccessToken, newRefreshToken);
    }



    public UserResponse getMemberById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return UserResponse.from(user);
    }
}
