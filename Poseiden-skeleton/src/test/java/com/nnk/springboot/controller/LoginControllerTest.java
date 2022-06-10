package com.nnk.springboot.controller;

import com.nnk.springboot.configuration.UserRole;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    User userTest = new User("user", "$2a$10$YWQo1YqaUDdbzJh5qDirbexNQsaYZmQ7yzn88wIA/HLGp6ihjuve2", "User" , UserRole.USER.name());
    User adminTest = new User("admin", "$2a$10$YWQo1YqaUDdbzJh5qDirbexNQsaYZmQ7yzn88wIA/HLGp6ihjuve2", "Admin" , UserRole.ADMIN.name());

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        userRepository.save(userTest);
        userRepository.save(adminTest);
    }

    @AfterEach
    public void setdown() {
        userRepository.delete(userTest);
        userRepository.delete(adminTest);
    }

    @Test
    public void shouldReturnDefaultMEssage() throws Exception{
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void userLoginTest() throws Exception{
        mockMvc.perform(formLogin("/login").user("user").password("12345678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list?userInfo=user"));
    }

    @Test
    public void adminLoginTest() throws Exception{
        mockMvc.perform(formLogin("/login").user("admin").password("12345678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list?userInfo=admin"));
    }

    @Test
    public void loginTest_Unknown() throws Exception{
        mockMvc.perform(formLogin("/login").user("coco").password("12345678"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}