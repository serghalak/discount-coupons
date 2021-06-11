package com.exadel.sandbox.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
//@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //@Value("${jwt.signing.key}")
    private String signingKey;

    public JwtAuthenticationFilter(String signingKey) {
        this.signingKey = signingKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader("Authorization");
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        String username = String.valueOf(claims.get("username"));
        //var authorities=(List<Map<String,String>>)claims.get("authorities");
        String role = String.valueOf(claims.get("authorities"));
        log.debug(">>>>>>>>>>>Role: " + role);
        //GrantedAuthority a = new SimpleGrantedAuthority("user");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                Collections.singletonList(new SimpleGrantedAuthority(role));
        var auth = new UsernamePasswordAuthenticationToken(username
                , null
                , simpleGrantedAuthorities);

        SecurityContextHolder.getContext()
                .setAuthentication(auth);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        log.debug(">>>>>>>>>>Check token is done");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}

