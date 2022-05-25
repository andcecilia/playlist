package com.letscode.service;

import com.letscode.dto.MusicDto;
import com.letscode.exception.MusicNotFoundException;
import com.letscode.exception.UserNotFoundException;

import java.util.List;

public interface MusicService {
    List<MusicDto> getMusicListFromUser(String username);

    void saveMusic(MusicDto musicDto) throws UserNotFoundException;

    void updateMusic(MusicDto musicDto) throws UserNotFoundException;

    void deleteMusicFromUser(String username, Long id) throws UserNotFoundException, MusicNotFoundException;
}
