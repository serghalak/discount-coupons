package com.exadel.sandbox.security;

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

//@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    public HeaderAuthenticationFilter headerAuthenticationFilter(AuthenticationManager authenticationManager){
//        HeaderAuthenticationFilter filter = new HeaderAuthenticationFilter(
//                new AntPathRequestMatcher("/api/**"));
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }


    @Value("${jwt.signing.key}")
    private String signingKey;

    public InitialAuthenticationFilter initialAuthenticationFilter(AuthenticationManager authenticationManager){
        InitialAuthenticationFilter filter =
                new InitialAuthenticationFilter(authenticationManager,signingKey);
        return filter;
    }
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        JwtAuthenticationFilter filter =
                new JwtAuthenticationFilter(signingKey);
        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.addFilterBefore(headerAuthenticationFilter(authenticationManager()),
//                UsernamePasswordAuthenticationFilter.class)
//        .csrf().disable();

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
//                .and()
//                .formLogin().and()
//                .httpBasic();
    }


}
