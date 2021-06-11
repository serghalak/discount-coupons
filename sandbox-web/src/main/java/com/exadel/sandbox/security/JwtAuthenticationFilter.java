package com.exadel.sandbox.security;

import com.exadel.sandbox.security.jwt.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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

        String token=jwt.replace(jwtConfig.getTokenPrefix(),"");

        SecretKey key = secretKey;

        Claims claims=null;

        try{
             claims= Jwts.parserBuilder()
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

            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            log.debug(">>>>>>>>>>Check token is done");
        }catch (JwtException e){
            log.error(">>>>>>>Token Authentication  Failed", e);
            unsuccessfulAuthentication(httpServletRequest, httpServletResponse, e);
        }
        return;
    }

    private void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response
                                        , JwtException failed)
            throws IOException, ServletException {

        SecurityContextHolder.clearContext();

        if (log.isDebugEnabled()) {
            log.debug(">>>>>Clear SecurityContext...Token Authentication request failed: "
                    + failed.toString(),failed);
        }

        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
        response.sendError(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}

