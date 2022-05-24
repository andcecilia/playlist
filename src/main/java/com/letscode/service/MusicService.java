package com.letscode.service;

import com.letscode.dto.MusicDto;

import java.util.List;

public interface MusicService {
    List<MusicDto> getMusicListFromUser(String username);

    void saveMusic(MusicDto musicDto);
}
