package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.notification.Subscription;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setPassword("pass");
        testUser.setSubscriptions(Set.of(
                new Subscription("CATEGORY", 1L, "testUser", testUser),
                new Subscription("VENDOR", 1L, "testUser", testUser),
                new Subscription("TAG", 1L, "testUser", testUser)
        ));

    }

    @Test
    void findAll() {
        ArrayList mockitoList = Mockito.mock(ArrayList.class);
        doAnswer(inn -> testUser).when(mockitoList).get(anyInt());
        userService.findAll();
        when(userService.findAll()).thenReturn(mockitoList);
        assertEquals(mockitoList.get(1), userService.findAll().get(1));

    }

    @Test
    void findByName() {
        UserResponse ur =UserResponse.builder()
                .email("testUser@gmail.com")
                .build();
        when(userService.findByName(testUser.getEmail())).thenReturn(ur);
        UserResponse actual = userService.findByName(testUser.getEmail());
        assertEquals(testUser.getEmail(), actual.getEmail());

    }

    @Test
    void findAllUsersByVendorSubscriptionTest() {

    }

    @Test
    void findAllUsersByCategorySubscriptionTest() {

    }

    @Test
    void findAllUsersByTagsSubscriptionTEst() {

    }

}