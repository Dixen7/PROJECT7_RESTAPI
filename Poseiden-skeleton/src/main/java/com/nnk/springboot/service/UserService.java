package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.DTO.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User createUser(UserDTO userDTO);

    Optional<User> findByIdAndUpdate(Integer id, UserDTO userDTO);

    Optional<User> findById(Integer id);

    void delete(User user);

    UserDTO userEntityToDTO(User userToUpdate);
}