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
    public ResponseEntity<ErrorDto> handleValidationException() {
        log.error("Error User");
        var errorDto =
                ErrorDto.builder()
                        .codigoErro(HttpStatus.BAD_REQUEST.value())
                        .mensagem("Invalid message")
                        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
        log.error("User Not Found: " + e);
        var erroDto =
                ErrorDto.builder()
                        .mensagem("User Not Found" + e.getMessage())
                        .codigoErro(HttpStatus.NOT_FOUND.value())
                        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
    }

    @ExceptionHandler(value = MusicNotFoundException.class)
    public ResponseEntity<ErrorDto> handleMusicNotFoundException(MusicNotFoundException e) {
        log.error("Music Not Found: " + e);
        var erroDto =
                ErrorDto.builder()
                        .mensagem("Music Not Found" + e.getMessage())
                        .codigoErro(HttpStatus.NOT_FOUND.value())
                        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalArgumentException e) {
        log.error("Argumento ilegal: " + e);
        var erroDto =
                ErrorDto.builder()
                        .mensagem("Ilegal Argument:" +e.getMessage())
                        .codigoErro(HttpStatus.NOT_FOUND.value())
                        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroDto);
    }
}
