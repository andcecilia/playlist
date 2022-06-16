package com.letscode.controller;

import com.letscode.dto.ErrorDto;
import com.letscode.exception.MusicNotFoundException;
import com.letscode.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleValidationException(ConstraintViolationException e) {
        log.error("Constraint Violation Exception" + e.getMessage());
        var errorDto =
                ErrorDto.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid message" )
                        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
        log.error("User Not Found: " + e);
        var errorDto =
                ErrorDto.builder()
                        .message(e.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = MusicNotFoundException.class)
    public ResponseEntity<ErrorDto> handleMusicNotFoundException(MusicNotFoundException e) {
        log.error("Music Not Found: " + e);
        var errorDto =
                ErrorDto.builder()
                        .message(e.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: " + e.getMessage());
        var errorDto =
                ErrorDto.builder()
                        .message("IllegalArgumentException: " +e.getMessage())
                        .errorCode(HttpStatus.CONFLICT.value())
                        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

}
