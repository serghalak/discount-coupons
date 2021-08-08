package com.exadel.sandbox.controllers;

import com.exadel.sandbox.model.notification.SubscriberEnum;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.*;
import com.exadel.sandbox.service.filter.FilterService;
import com.exadel.sandbox.service.impl.UserSecurityServiceImpl;
import com.exadel.sandbox.service.impl.ViewedEventService;
import com.exadel.sandbox.service.notification.SubscriberService;
import com.exadel.sandbox.service.statistics.StatisticsReportToExcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@PropertySource({"classpath:testing.properties"})
public class CategoryControllerIT {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @MockBean
    EventService eventService;

    @MockBean
    ViewedEventService viewedEventService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    FavouriteService favouriteService;

    @MockBean
    FilterService filterService;

    @MockBean
    OrderService orderService;

    @MockBean
    UserService userService;

    @MockBean
    UserSecurityServiceImpl userSecurityService;

    @MockBean
    CityService cityService;

    @MockBean
    CountryService countryService;

    @MockBean
    LocationService locationService;

    @MockBean
    StatisticsService statisticsService;

    @MockBean
    TagService tagService;

    @MockBean
    VendorDetailsService vendorDetailsService;

    @MockBean
    VendorService vendorService;

    @MockBean
    SubscriberService subscriberService;

    @MockBean
    StatisticsReportToExcel statisticsReportToExcel;

//    @Value("${spring.security.user.name}")
//    String username;
//
//    @Value("${spring.security.user.password}")
//    String password;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("spring")
    @Test
    void findCategories() throws Exception{
        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk());
    }


//    @Test
//    void findCategoriesWithHttpBasicAuthentication() throws Exception{
//
//        System.out.println(">>>Username: " + username);
//        System.out.println(">>>Password: " + password);
//
//        mockMvc.perform(get("/api/category")
//                .with(httpBasic("svitlana@gmail.com","12345")))
//                .andExpect(status().isOk());
//    }
}
