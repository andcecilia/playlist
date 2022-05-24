package com.letscode.controller;

import com.letscode.dto.MusicDto;
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
    public ResponseEntity<List<MusicDto>> getMusicListFromUser(@PathVariable(name="username") final String username) {

        final List<MusicDto> todoDtoList =  service.getMusicListFromUser(username);

        return ResponseEntity.ok(todoDtoList);
    }

    @PostMapping(path = "/music")
    public ResponseEntity<Void> saveMusic(@RequestBody MusicDto musicDto) {

        log.info(musicDto);
        service.saveMusic(musicDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //PUT --> UPDATE
    @PutMapping(path = "/myplaylist")
    public ResponseEntity<Void> putPlaylist(@RequestBody UserDto playlistDTO) {

        log.info("PUT: " + playlistDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(path = "/myplaylist/{singer}/{song}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable(name = "singer") String singer,
                                               @PathVariable(name = "song") String song) {

        log.info("APAGANDO " + singer + " " + song);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
