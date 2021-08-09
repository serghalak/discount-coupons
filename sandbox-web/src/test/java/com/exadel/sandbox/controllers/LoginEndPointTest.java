package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.WebSecurityConfig;
import com.exadel.sandbox.security.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class LoginEndPointTest extends BaseIT{


    @Test
    void availableRootEndPointWithoutAuthentication() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
}
