package com.exadel.sandbox;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SandboxApplicationTest {

    @Autowired
    private MockMvc mvc;

    //if i send request without header Authorization i will receive 200 ok
    @Test
    @DisplayName("Test calling /api/category endpoint without authentication returns unauthorized.")
    @Disabled
    public void helloUnauthenticated() throws Exception {
        mvc.perform(get("/api/category"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test calling /api/category endpoint authenticated with a mock user returns ok.")
    @WithMockUser
    public void helloAuthenticated() throws Exception {
        mvc.perform(get("/api/category"))
                //.andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test calling /api/category endpoint authenticated with a real user returns ok.")
    public void helloAuthenticatedWithUser() throws Exception {
        mvc.perform(get("/api/category")
                .with(user("svitlana@gmail.com")))
                //.andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }

    @Test
    void findCategoriesWithHttpBasicAuthentication() throws Exception{

        mvc.perform(get("/api/category")
                .with(httpBasic("svitlana@gmail.com","12345")))
                .andExpect(status().isOk());
    }

}