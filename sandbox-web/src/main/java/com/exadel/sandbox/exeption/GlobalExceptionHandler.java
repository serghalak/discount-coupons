package com.exadel.sandbox.exeption;

import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*400*/
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> illegalArgumentExceptionHandler(HttpServletRequest request, IllegalArgumentException exception) {
        return getResponse(request, HttpStatus.BAD_REQUEST, exception);
    }

    /*401*/
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> badCredentialsExceptionHandler(HttpServletRequest request, AuthenticationException exception) {
        return getResponse(request, HttpStatus.UNAUTHORIZED, exception);
    }

    /*403*/
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<?> accessDeniedExceptionHandler(HttpServletRequest request, AccessDeniedException exception) {
        return getResponse(request, HttpStatus.FORBIDDEN, exception);
    }

    /*404*/
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> entityNotFoundExceptionHandler(HttpServletRequest request, EntityNotFoundException exception) {
        return getResponse(request, HttpStatus.NOT_FOUND, exception);
    }

    /*409*/
    @ExceptionHandler(DuplicateNameException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<?> duplicateNameExceptionHandler(HttpServletRequest request, DuplicateNameException exception) {
        return getResponse(request, HttpStatus.CONFLICT, exception);
    }

    private ResponseEntity<?> getResponse(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {
        log.error("Exception raised = {} :: URL = {}", exception.getMessage(), request.getRequestURL());
        Map<String, Object> response = new HashMap<>();
        response.put("code", httpStatus.value() + " / " + httpStatus.getReasonPhrase());
        response.put("message", exception.getMessage());
        response.put("path", request.getRequestURI());
        return new ResponseEntity<>(response, httpStatus);
    }
}
