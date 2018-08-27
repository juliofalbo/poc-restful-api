package com.julio.restfulws.services;

import com.julio.restfulws.annotations.PopulateUserOnPost;
import com.julio.restfulws.entities.User;
import com.julio.restfulws.exceptions.UserNotFoundException;
import com.julio.restfulws.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("No user found for id: %s", id)));
    }

    public void deleteById(Long id) {
        repository.delete(findOne(id));
    }

}