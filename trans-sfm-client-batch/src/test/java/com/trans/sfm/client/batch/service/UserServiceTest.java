package com.trans.sfm.client.batch.service;

import com.trans.sfm.client.batch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveBatch() {
        User user1 = new User();
        user1.setName("Test User 1");
        user1.setAge(25);
        user1.setEmail("test1@example.com");

        User user2 = new User();
        user2.setName("Test User 2");
        user2.setAge(30);
        user2.setEmail("test2@example.com");

        boolean result = userService.saveBatch(Arrays.asList(user1, user2));
        assertTrue(result);
    }

    @Test
    public void testFindUsers() {
        List<User> users = userService.list();
        assertNotNull(users);
    }
}