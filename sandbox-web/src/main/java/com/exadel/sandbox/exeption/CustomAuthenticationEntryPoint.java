package com.exadel.sandbox.exeption;

import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
public class CustomAuthenticationEntryPoint {

//    @Override
//    @ExceptionHandler(value = {BadCredentialsException.class})
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException e) throws IOException, ServletException {
//        //401
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        //403
        response.sendError(HttpServletResponse.SC_FORBIDDEN,
                "Authorization Failed : " + accessDeniedException.getMessage());
    }

//    @ExceptionHandler(EntityNotFoundException.class)
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         EntityNotFoundException entityNotFoundException) throws IOException {
//        //404
//        response.sendError(HttpServletResponse.SC_NOT_FOUND,
//                "Requested resource is not available : " + entityNotFoundException.getMessage());
//    }

    @ExceptionHandler(DuplicateNameException.class)
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         DuplicateNameException exception) throws IOException {
        //409
        response.sendError(HttpServletResponse.SC_CONFLICT,
                "Requested resource is not available : " + exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         MethodArgumentNotValidException exception) throws IOException {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        //400
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         IllegalArgumentException exception) throws IOException {
        //400
        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                "Requested resource is not available : " + exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> entityNotFoundExceptionHandler(HttpServletRequest request, EntityNotFoundException exception) {
        return getResponse(request, HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> badCredentialsExceptionHandler(HttpServletRequest request, AuthenticationException exception) {
        return getResponse(request, HttpStatus.UNAUTHORIZED, exception);
    }

    private ResponseEntity<?> getResponse(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {
        log.error("Exception raised = {} :: URL = {}", exception.getMessage(), request.getRequestURL());
        Map<String, Object> response = new HashMap<>();
        response.put("code", httpStatus.value() + " / " + httpStatus.getReasonPhrase());
        response.put("message", exception.getMessage());
//        response.put("path", request.getRequestURL());
        response.put("path", request.getRequestURI());
        return new ResponseEntity<>(response, httpStatus);
    }
}
