package com.nnk.springboot.service;

import com.nnk.springboot.domain.DTO.UserDTO;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(userDTOToUserEntity(userDTO));
    }

    @Override
    public Optional<User> findByIdAndUpdate(Integer id, UserDTO userDTO) {

        User userToUpdate = findById(id).get();

        if (userDTO.getUsername() != null && !userDTO.getUsername().isEmpty()) {
            userToUpdate.setUsername(userDTO.getUsername());
        }
        if (userDTO.getFullname() != null && !userDTO.getFullname().isEmpty()) {
            userToUpdate.setFullname(userDTO.getFullname());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) {
            userToUpdate.setRole(userDTO.getRole());
        }
        userRepository.save(userToUpdate);

        return Optional.of(userToUpdate);
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        return user;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    private User userDTOToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setRole(userDTO.getRole());
        return user;
    }

    public UserDTO userEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(passwordEncoder.encode(user.getPassword()));
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

}