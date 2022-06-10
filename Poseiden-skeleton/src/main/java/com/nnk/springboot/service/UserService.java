package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.User;

public interface UserService {

    List<User> getAllUser();

    User saveUser(User user);

    User updateUser(Integer id, User user);

    User getUserById(Integer id);

    boolean deleteUserById(Integer id);

    User getUserByUserName(String username);

}