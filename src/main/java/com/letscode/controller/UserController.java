package com.letscode.controller;

import com.letscode.dto.UserDto;
import com.letscode.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(path="/user")
    public ResponseEntity<Void> saveUser(@RequestBody final UserDto userDto){

        log.info("POST: " + userDto);
        service.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/user")
    public ResponseEntity<List<UserDto>> getAll() {

        List<UserDto> userDtoList =  service.getAll();
        return ResponseEntity.ok(userDtoList);
    }


}
