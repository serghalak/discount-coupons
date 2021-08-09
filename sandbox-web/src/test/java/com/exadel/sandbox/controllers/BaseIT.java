package com.exadel.sandbox.controllers;

import com.exadel.sandbox.security.WebSecurityConfig;
import com.exadel.sandbox.security.config.SecurityConfig;
import com.exadel.sandbox.security.filters.InitialAuthenticationFilter;
import com.exadel.sandbox.security.utill.JwtUtil;
import com.exadel.sandbox.service.*;
import com.exadel.sandbox.service.filter.FilterService;
import com.exadel.sandbox.service.impl.UserSecurityServiceImpl;
import com.exadel.sandbox.service.impl.ViewedEventService;
import com.exadel.sandbox.service.notification.SubscriberService;
import com.exadel.sandbox.service.statistics.StatisticsReportToExcel;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.FilterChain;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Import(SecurityConfig.class)
public abstract class BaseIT {

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



//    @MockBean
//    WebSecurityConfig webSecurityConfig;
//
//    @MockBean
//    AuthenticationManager authenticationManager;



    @BeforeEach
    void setUp(){

        mockMvc= MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}
