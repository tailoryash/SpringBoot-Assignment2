package com.yashtailor.assignment2.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> exception(){
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
