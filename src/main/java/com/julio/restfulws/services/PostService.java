package com.julio.restfulws.services;

import com.julio.restfulws.annotations.PopulateUserOnPost;
import com.julio.restfulws.annotations.ValidateUser;
import com.julio.restfulws.entities.Post;
import com.julio.restfulws.entities.User;
import com.julio.restfulws.exceptions.PostNotFoundException;
import com.julio.restfulws.exceptions.UserNotFoundException;
import com.julio.restfulws.repositories.PostRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserService userService;

    @ValidateUser
    public List<Post> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @PopulateUserOnPost(positionOfUserId = "1", positionOfPostObject = "0")
    public Post save(Post post, Long userId) {
        return repository.save(post);
    }

    @ValidateUser
    public Post findOne(Long userId, Long id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException(String.format("No post found for id: %s", id)));
    }

    @ValidateUser(positionOfUserId = "1")
    public void deleteById(Long id, Long userId)
    {
        repository.delete(findOne(userId, id));
    }

}