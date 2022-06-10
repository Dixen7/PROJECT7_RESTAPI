package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import com.nnk.springboot.configuration.UserRole;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "clement", authorities = {"ADMIN"})
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> listUserTest;
    private User userValidTest;

    @BeforeEach
    public void setup() {
        User userTest = new User("Username", "Password", "Fullname" , UserRole.USER.name());
        userValidTest = new User("Username valid", "Password valid", "Fullname valid" , UserRole.USER.name());
        listUserTest = new ArrayList<User>();
        listUserTest.add(userTest);
    }

    @Test
    public void testHome() throws Exception{
        when(userService.getAllUser()).thenReturn(listUserTest);
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void testAddUser() throws Exception{
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(userService.saveUser(userValidTest)).thenReturn(userValidTest);
        mockMvc.perform(post("/user/validate")
                        .param("username", "Username valid")
                        .param("password", "T3$tauto")
                        .param("fullname", "Fullname valid")
                        .param("role", UserRole.USER.name()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(userService.saveUser(userValidTest)).thenReturn(userValidTest);
        mockMvc.perform(post("/user/validate")
                        .param("username", "Username valid")
                        .param("password", "Password invalid")
                        .param("fullname", "Fullname valid")
                        .param("role", UserRole.USER.name()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeHasFieldErrors("user", "password"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(userService.getUserById(1)).thenReturn(userValidTest);
        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(userService.saveUser(userValidTest)).thenReturn(userValidTest);
        mockMvc.perform(post("/user/update/{id}", "1")
                        .param("username", "Username update")
                        .param("password", "Password update")
                        .param("fullname", "Fullname update")
                        .param("role", UserRole.USER.name()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(userService.saveUser(userValidTest)).thenReturn(userValidTest);
        mockMvc.perform(post("/user/update/{id}", "1")
                        .param("username", "Username update")
                        .param("password", "Password update")
                        .param("fullname", "Fullname update")
                        .param("role", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeHasFieldErrors("user", "role"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/user/delete/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

}