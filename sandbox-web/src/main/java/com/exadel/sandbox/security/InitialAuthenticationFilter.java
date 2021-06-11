package com.exadel.sandbox.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
import java.util.Map;

@Slf4j
//@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {


    private AuthenticationManager authenticationManager;

    public InitialAuthenticationFilter(AuthenticationManager authenticationManager
            ,String signingKey) {
        this.authenticationManager = authenticationManager;
        this.signingKey = signingKey;
    }

    //@Value("${jwt.signing.key}")
    private String signingKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain chain) throws ServletException, IOException {

        logger.debug(">>>>>>>>>>>>>>>>InitialAuthenticationFilter");
        if (logger.isDebugEnabled()) {
            logger.debug(">>>>>>Request is to process authentication " +
                    "in InitialAuthenticationFilter");
        }

        try {

            Authentication authResult = attemptAuthentication(request, response);

            if (authResult != null) {
                successfulAuthentication(request, response, chain, authResult);
            } else {
                chain.doFilter(request, response);
            }
        } catch (AuthenticationException e) {
            log.error(">>>>>>>Authentication Failed", e);
            unsuccessfulAuthentication(request, response, e);
        }


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        return !request.getServletPath().equals("/login");
    }


    //-------------------------------------------------------------------

    private Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String userName=getUsername(httpServletRequest);
        String password=getPassword(httpServletRequest);
        if(userName==null){
            userName="";
        }
        if(password==null){
            password="";
        }

        log.debug(">>>>>HeaderAuthenticationFilte User: " + userName);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userName, password);
        if(!userName.isEmpty()){
            return authenticationManager.authenticate(token);
        }else{
            return null;
        }

    }


    private void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        String username=getUsername(request);

        SecretKey key = Keys.hmacShaKeyFor(
                signingKey.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setClaims(Map.of("username", username,"authorities",authResult.getAuthorities()))
                .signWith(key)
                .compact();

        response.addHeader("Authorization", jwt);

        SecurityContextHolder.getContext().setAuthentication(authResult);

    }


    private void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        SecurityContextHolder.clearContext();

        if (log.isDebugEnabled()) {
            log.debug(">>>>>Clear SecurityContext... Authentication request failed: "
                    + failed.toString(),failed);
        }

        response.sendError(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    private String getPassword(HttpServletRequest request) {

        return request.getHeader("Api-Secret");
    }

    private String getUsername(HttpServletRequest request) {

        return request.getHeader("Api-Key");
    }

}
