package com.exadel.sandbox.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@PropertySource({"classpath:testing.properties"})
public class CategoryControllerIT extends BaseIT {

    private static final String JSON_BODY="{\n" +
            "    \"id\": 25,\n" +
            "    \"name\": \"transort\",\n" +
            "    \"description\": \"Info about rent cars\",\n" +
            "    \"tags\": [\n" +
            "        {\n" +
            "            \"id\": 125,\n" +
            "            \"name\": \"track11\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 115,\n" +
            "            \"name\": \"miniven\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 135,\n" +
            "            \"name\": \"sport cars\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 105,\n" +
            "            \"name\": \"jeep\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\":\"bus\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\":\"jeep\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    @WithMockUser("spring")
    void deleteCategoryByIdWithAuthority() throws Exception {
        mockMvc.perform(delete("/api/category/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCategoryByIdWithoutAuthority() throws Exception {
        mockMvc.perform(delete("/api/category/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser("spring")
    void updateCategoryWithAuthority() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON_BODY);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void updateCategoryWithoutAuthority() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON_BODY);

        mockMvc.perform(request)
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser("spring")
    void findCategoryByOfPartNameCategoryWithAuthority() throws Exception {
        mockMvc.perform(get("/api/categoryname/somepartofname"))
                .andExpect(status().isOk());
    }

    @Test
    void findCategoryByOfPartNameCategoryWithoutAuthority() throws Exception {
        mockMvc.perform(get("/api/categoryname/somepartofname"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser("spring")
    void findCategoryByIdWithAuthority() throws Exception {
        mockMvc.perform(get("/api/category/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findCategoryByIdWithoutAuthority() throws Exception {
        mockMvc.perform(get("/api/category/1"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser("spring")
    void getAllCategoriesWithAuthority() throws Exception {
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCategoriesWithoutAuthority() throws Exception {
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser("spring")
    void createCategoryWithAuthority() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON_BODY);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void createCategoryWithoutAuthority() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSON_BODY);

        mockMvc.perform(request)
                .andExpect(status().isUnauthorized());
    }


    @Test
    @Disabled
    void findCategoriesWithHttpBasicAuthentication() throws Exception{

        mockMvc.perform(get("/api/category")
                .with(httpBasic("spring","guru")))
                .andExpect(status().isOk());
    }
}
