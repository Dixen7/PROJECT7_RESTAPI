package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testShowUserAdmin() throws Exception {
        this.mockMvc.perform(get("/user/list")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "USER")
    @Test
    public void testShowUser() throws Exception {
        this.mockMvc.perform(get("/user/list")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "USER")
    @Test
    public void testAddUserUser() throws Exception {
        this.mockMvc.perform(get("/user/add")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testAddUserAdmin() throws Exception {
        this.mockMvc.perform(get("/user/add")).andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testValidateUserAdmin() throws Exception {
        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "fullname")
                .param("username", "username")
                .param("password", "Test123!*")
                .param("role", "ADMIN")
                .with(csrf())
        ).andExpect(redirectedUrl("/user/list"));
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testValidateUserAdminHasError() throws Exception {
        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "fullname")
                .param("username", "username")
                .param("password", "password")
                .param("role", "USER")
                .with(csrf())
        ).andExpect(model().hasErrors());
    }

    @WithMockUser
    @Test
    public void testValidateUser() throws Exception {
        this.mockMvc.perform(post("/user/validate")
                .param("fullname", "fullname")
                .param("username", "username")
                .param("password", "Test123!")
                .param("role", "USER")
                .with(csrf())
        ).andExpect(redirectedUrl("/user/list"));
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testUpdateUserAdmin() throws Exception {
        User user = userRepository.save(new User("Clement", "Test1234!", "Clement", "ADMIN"));
        this.mockMvc.perform(post("/user/update/" + user.getId())
                .param("fullname", "Clement")
                .param("username", "clement")
                .param("password", "Test12345!")
                .param("role", "USER")
                .with(csrf())
        ).andExpect(redirectedUrl("/user/list"));
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testUpdateUserAdminHasError() throws Exception {
        User user = userRepository.save(new User("Username", "Test*1234!", "Fullname", "ADMIN"));
        this.mockMvc.perform(post("/user/update/" + user.getId())
                .param("fullname", "fullname")
                .param("username", "username")
                .param("password", "password")
                .param("role", "USER")
                .with(csrf())
        ).andExpect(model().hasErrors());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    public void testDeleteUserAdmin() throws Exception {
        User user = userRepository.save(new User("Username", "Aaaaaaaa7*", "Fullname", "ADMIN"));
        this.mockMvc.perform(get("/user/delete/" + user.getId())).andExpect(status().isFound()).andReturn();
    }
}