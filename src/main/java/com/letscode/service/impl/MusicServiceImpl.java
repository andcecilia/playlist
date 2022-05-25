package com.letscode.service.impl;

import com.letscode.domain.model.Music;
import com.letscode.domain.model.User;
import com.letscode.domain.repository.MusicRepository;
import com.letscode.dto.MusicDto;
import com.letscode.service.MusicService;
import com.letscode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final UserService userService;
    private final MusicRepository repository;

    @Override
    public List<MusicDto> getMusicListFromUser(String username) {

        final User userEntity = userService.findUserByUsername(username);

        return repository
                .findAllByUserEntityEquals(userEntity)
                .stream()
                .map(musicEntity -> new MusicDto(musicEntity.getId(), musicEntity.getTitle(), musicEntity.getMusicalGender(), username))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMusic(MusicDto musicDto) {

        final User userEntity = userService.findUserByUsername(musicDto.getUsername());
        final Music musicEntity = Music.builder()
                .title(musicDto.getTitle())
                .musicalGender(musicDto.getMusicalGender())
                .userEntity(userEntity)
                .build();
        repository.save(musicEntity);
    }

    @Override
    public void updateMusic(MusicDto musicDto) {
        final User userEntity = userService.findUserByUsername(musicDto.getUsername());
        final Music musicEntity = Music.builder()
                .id(musicDto.getId())
                .title(musicDto.getTitle())
                .musicalGender(musicDto.getMusicalGender())
                .userEntity(userEntity)
                .build();
        repository.save(musicEntity);
    }

    @Override
    public void deleteMusicFromUser(String username, Long id) {

            userService.findUserByUsername(username);
            final Music musicEntity = findMusicById(id);
            repository.delete(musicEntity);
        }

    private Music findMusicById(Long id) {
        return repository.findById(id).orElseThrow();
    }


}
