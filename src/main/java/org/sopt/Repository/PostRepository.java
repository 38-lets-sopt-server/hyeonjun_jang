package org.sopt.Repository;

import org.sopt.Domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//데이터를 저장하고 꺼내는 일만 한다
@Repository
public class PostRepository {
    private static List<Post> postList = new ArrayList<>();

    private Long nextId = 1L;

    public Post save(Post post) {
        postList.add(post);
        return post;
    }

    public List<Post> findAll() {
        return postList;
    }

    public Optional<Post> findById(Long id) {
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return postList.removeIf(p -> p.getId().equals(id));
    }

    public Long generateId() {
        return nextId++;
    }
}