package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserTest {

    private User user;

    @Test
    public void setAndGetId() {
        user = new User();
        user.setId(1);
        Assert.assertTrue(user.getId() == 1);
    }

    @Test
    public void setAndGetUsername() {
        user = new User();
        user.setUsername("Username");
        Assert.assertTrue(user.getUsername().equals("Username"));
    }

    @Test
    public void setAndGetPassword() {
        user = new User();
        user.setPassword("Password");
        Assert.assertTrue(user.getPassword().equals("Password"));
    }

    @Test
    public void setAndGetFullname() {
        user = new User();
        user.setFullname("Fullname");
        Assert.assertTrue(user.getFullname().equals("Fullname"));
    }

    @Test
    public void setAndGetRole() {
        user = new User();
        user.setRole("Role");
        Assert.assertTrue(user.getRole().equals("Role"));
    }
}