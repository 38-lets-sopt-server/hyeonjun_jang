package org.sopt.Domain.user.service;


import org.sopt.Domain.user.dto.response.CreateUserResponse;
import org.sopt.Domain.user.dto.response.UserResponse;
import org.sopt.Domain.user.entity.User;
import org.sopt.Domain.user.exception.code.UserErrorCode;
import org.sopt.Domain.user.exception.UserException;
import org.sopt.Domain.user.repository.UserRepository;
import org.sopt.Domain.user.dto.request.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new UserException(UserErrorCode.USER_EMAIL_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(createUserRequest.password());

        User savedUser = userRepository.save(new User(
                        createUserRequest.nickname(),
                        createUserRequest.email(),
                        encodedPassword
                )
        );
        return CreateUserResponse.from(savedUser);
    }

    public UserResponse findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> {
            return new UserException(UserErrorCode.USER_NOT_FOUND);
        });
        return UserResponse.from(user);
    }
}