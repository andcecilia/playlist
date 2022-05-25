package com.letscode.controller;

import com.letscode.dto.MusicDto;
import com.letscode.exception.MusicNotFoundException;
import com.letscode.exception.UserNotFoundException;
import com.letscode.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MusicController {

    private final MusicService service;

    @GetMapping(path="/users/{username}/music")
    public ResponseEntity<List<MusicDto>> getMusicListFromUser(@PathVariable(name="username") final String username)
            throws UserNotFoundException {

        final List<MusicDto> todoDtoList =  service.getMusicListFromUser(username);

        return ResponseEntity.ok(todoDtoList);
    }

    @PostMapping(path = "/music")
    public ResponseEntity<Void> saveMusic(@RequestBody MusicDto musicDto) throws UserNotFoundException {

        log.info(musicDto);
        service.saveMusic(musicDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/music")
    public ResponseEntity<Void> updateMusic(@RequestBody MusicDto musicDto) throws UserNotFoundException {

        log.info("UPDATE: " + musicDto);
        service.updateMusic(musicDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/music/{username}/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable(name = "username") String username,
                                               @PathVariable(name = "id") Long id) throws MusicNotFoundException {

        log.info("APAGANDO " + username + " " + id);
        service.deleteMusicFromUser(username,id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
