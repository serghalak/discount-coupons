package com.exadel.sandbox.security;

import com.exadel.sandbox.security.jwt.JwtConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SecretKey secretKey;
    private JwtConfig jwtConfig;

    public InitialAuthenticationFilter initialAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        InitialAuthenticationFilter filter =
                new InitialAuthenticationFilter(authenticationManager(), secretKey,jwtConfig);
        return filter;
    }
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        JwtAuthenticationFilter filter =
                new JwtAuthenticationFilter(secretKey,jwtConfig);
        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .addFilterAt(initialAuthenticationFilter(authenticationManager())
                        , BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);

        http
                .authorizeRequests(authorize -> {
                    authorize.antMatchers("/",  "/login" ).permitAll();
                } )
                .authorizeRequests()
                .anyRequest().authenticated();

    }


}
