package com.exadel.sandbox.exeption;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class WebAppExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         EntityNotFoundException entityNotFoundException) throws IOException {

        response.sendError(HttpServletResponse.SC_NOT_FOUND,
                "Requested resource is not available : " + entityNotFoundException.getMessage());
    }

}
