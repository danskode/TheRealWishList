package org.kea.therealwishlist.service;

import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User loginUser(String username, String password) {
        return userRepository.loginUser(username, password);
    }
}