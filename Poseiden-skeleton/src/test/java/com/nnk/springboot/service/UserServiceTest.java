package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.nnk.springboot.configuration.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    User userTest = new User("UserName", "Password", "FullName", UserRole.USER.name());
    User userUpdateTest = new User("UserName updaTest", "Password updaTest", "FullName updaTest", UserRole.USER.name());
    User userDeleteTest = new User("UserName deleTest", "Password deleTest", "FullName deleTest", UserRole.USER.name());

    @BeforeEach
    public void setDb() {
        userRepository.deleteAll();
        userService.saveUser(userTest);
        userService.saveUser(userDeleteTest);
        userService.saveUser(userUpdateTest);
    }

    @Test
    public void testGetAllUser() {
        List<User> userListTest = userService.getAllUser();
        assertNotNull(userListTest);
        assertTrue(userListTest.size()>0);
    }

    @Test
    public void testSaveUser() {
        User saveUserTest = new User("UserName saveTest", "Password saveTest", "FullName saveTest", UserRole.USER.name());
        saveUserTest = userService.saveUser(saveUserTest);
        assertNotNull(saveUserTest);
        assertEquals("UserName saveTest",saveUserTest.getUsername());
    }

    @Test
    public void testSaveUser_Null() {
        User saveUserTest = null;
        saveUserTest = userService.saveUser(saveUserTest);
        assertNull(saveUserTest);
    }

    @Test
    public void testupdateUser() {
        userUpdateTest = userService.getUserByUserName(userUpdateTest.getUsername());
        Integer tradeUpdateIdUser = userUpdateTest.getId();
        User updateUserTest = new User("UserName Testupdt", "Password Testupdt", "FullName Testupdt", UserRole.USER.name());
        updateUserTest = userService.updateUser(tradeUpdateIdUser, updateUserTest);
        assertNotNull(updateUserTest);
        assertEquals("FullName Testupdt",updateUserTest.getFullname());
    }

    @Test
    public void testupdateUser_Null() {
        User updateUserTest = new User("UserName Testupdt", "Password Testupdt", "FullName Testupdt", UserRole.USER.name());
        updateUserTest = userService.updateUser(999999999, updateUserTest);
        assertNull(updateUserTest);
    }

    @Test
    public void testgetUserById() {
        userTest = userService.getUserByUserName(userTest.getUsername());
        Integer userIdTest = userTest.getId();
        User userByIdTest = userService.getUserById(userIdTest);
        assertNotNull(userByIdTest);
        assertEquals("UserName", userByIdTest.getUsername());
    }

    @Test
    public void testgetUserById_Null() {
        User userByIdTest = userService.getUserById(999999999);
        assertNull(userByIdTest);
    }

    @Test
    public void testdeleteUserById() {
        userDeleteTest = userService.getUserByUserName(userDeleteTest.getUsername());
        Integer userDeleteIdTest = userDeleteTest.getId();
        userService.deleteUserById(userDeleteIdTest);
        assertNull(userService.getUserById(userDeleteIdTest));
    }

    @Test
    public void testdeleteUserById_False() {
        assertFalse(userService.deleteUserById(999999999));
    }

}