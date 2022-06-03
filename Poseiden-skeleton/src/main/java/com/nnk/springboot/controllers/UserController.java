package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.DTO.UserDTO;
import com.nnk.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/user/list")
    public String home(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid UserDTO userDTO, BindingResult result) {
        if (!result.hasErrors()) {
            userService.createUser(userDTO);
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User userToUpdate = userService.findById(id).get();
        UserDTO userDTO = userService.userEntityToDTO(userToUpdate);
        log.info("Finding user ${userToUpdate} data");
        model.addAttribute("userDTO", userDTO);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDTO userDTO, BindingResult result) {

        if (!userDTO.getPassword().isEmpty() && result.hasErrors()) {
            return "user/update";
        }
        try {
            Optional<User> userToUpdate = userService.findByIdAndUpdate(id, userDTO);
            log.info("Updating data from user: " + userToUpdate.toString());
        } catch (Exception e) {
            log.info("Error while updating user data" + e);
        }
        return "redirect:/user/list";
    }


    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        try {
            Optional<User> userToDelete = userService.findById(id);
            if (userToDelete.isPresent()) {
                log.info("Deleting user " + userToDelete);
                userService.delete(userToDelete.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while deleting rating with id:" + id);
        }
        return "redirect:/user/list";
    }
}
