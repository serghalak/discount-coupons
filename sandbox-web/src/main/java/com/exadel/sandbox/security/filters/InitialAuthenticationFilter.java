package com.exadel.sandbox.security.filters;

import com.exadel.sandbox.security.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private SecurityConfig securityConfig;

    public InitialAuthenticationFilter(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.debug("Filters: InitialAuthenticationFilter: " + securityConfig.getSecretKey());
    }
}
