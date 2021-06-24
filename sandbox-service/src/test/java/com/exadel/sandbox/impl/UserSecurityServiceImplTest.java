package com.exadel.sandbox.impl;

import com.exadel.sandbox.service.impl.UserSecurityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserSecurityServiceImplTest {

    @Autowired
    private UserSecurityServiceImpl userSecurityService;

    @Test
    void loadUserByUsername() {
    }
}