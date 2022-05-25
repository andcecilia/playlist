package com.letscode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MusicNotFoundException extends Exception {
    public MusicNotFoundException(String msg) {
        super(msg);
    }
}
