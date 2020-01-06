package com.goruslan.eCommerce.service;

import com.goruslan.eCommerce.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User saveUser(User user);

    // Method 'save' can be used for updating and inserting operation
    User updateUser(User user);

    void deleteUser(Long userId);

    User findByUsername(String username);

    List<User> findAllUsers();

    Long numberOfUsers();
}
