package com.exadel.sandbox.security;

import com.exadel.sandbox.security.jwt.JwtConfig;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private SecretKey secretKey;
    private JwtConfig jwtConfig;

    public JwtAuthenticationFilter(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader(jwtConfig.getAuthorizationHeader());

        if(Strings.isNullOrEmpty(jwt) || !jwt.startsWith(jwtConfig.getTokenPrefix())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String token=jwt.replace(jwtConfig.getTokenPrefix(),"");

        SecretKey key = secretKey;

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
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

