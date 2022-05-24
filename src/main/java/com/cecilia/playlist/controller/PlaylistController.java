package com.cecilia.playlist.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class PlaylistController {

    //GET METHOD
    @GetMapping(path = "/myplaylist/{singer}/{song}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable(name = "singer") String singer,
                                                   @PathVariable(name = "song") String song) {

        var playlist = new PlaylistDTO();
        playlist.setSinger(singer);
        playlist.setSong(song);
        //helloWorld.setMensagem("Bem-vinda " + nome);

        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    //POST --> CREATE
    @PostMapping(path = "/myplaylist")
    public ResponseEntity<Void> postPlaylist(@RequestBody PlaylistDTO playlistDTO) {

        log.info(playlistDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //PUT --> UPDATE
    @PutMapping(path = "/myplaylist")
    public ResponseEntity<Void> putPlaylist(@RequestBody PlaylistDTO playlistDTO) {

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
