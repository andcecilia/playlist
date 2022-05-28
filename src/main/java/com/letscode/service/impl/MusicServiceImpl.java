package com.letscode.service.impl;

import com.letscode.domain.model.Music;
import com.letscode.domain.model.User;
import com.letscode.domain.repository.MusicRepository;
import com.letscode.dto.MusicDto;
import com.letscode.exception.MusicNotFoundException;
import com.letscode.exception.UserNotFoundException;
import com.letscode.service.MusicService;
import com.letscode.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
@RequiredArgsConstructor
@Log4j2
public class MusicServiceImpl implements MusicService {

    private final UserService userService;
    private final MusicRepository repository;

    @Override
    public List<MusicDto> getMusicListFromUser(final String username) throws UserNotFoundException{

        checkNotNull(username, "null username");
        final User userEntity = userService.findUserByUsername(username);

        return repository
                .findAllByUserEntityEquals(userEntity)
                .stream()
                .map(musicEntity -> new MusicDto(musicEntity.getId(), musicEntity.getTitle(), musicEntity.getArtist(), username))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMusic(final MusicDto musicDto) throws UserNotFoundException{

        checkNotNull(musicDto);

        final User userEntity = userService.findUserByUsername(musicDto.getUsername());
        final Music musicEntity = Music.builder()
                .title(musicDto.getTitle())
                .artist(musicDto.getArtist())
                .userEntity(userEntity)
                .build();
        repository.save(musicEntity);
    }

    @Override
    public void updateMusic(final MusicDto musicDto) throws UserNotFoundException {

        checkNotNull(musicDto);

        final User userEntity = userService.findUserByUsername(musicDto.getUsername());
        final Music musicEntity = Music.builder()
                .id(musicDto.getId())
                .title(musicDto.getTitle())
                .artist(musicDto.getArtist())
                .userEntity(userEntity)
                .build();
        repository.save(musicEntity);
    }

    @Override
    public void deleteMusicFromUser(final String username, final Long id) throws UserNotFoundException, MusicNotFoundException {

        try {
            checkNotNull(username);
            checkArgument(id > 0);

            userService.findUserByUsername(username);
            final Music musicEntity = findMusicById(id);
            repository.delete(musicEntity);

        } catch (UserNotFoundException e) {
            log.error("User not found: " + username);
            log.error(e.getMessage());
            throw e;
        } catch (MusicNotFoundException e) {
            log.error("Music not found: " + id);
            log.error(e.getMessage());
            throw e;
        }
    }

    private Music findMusicById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("Music not found"));
    }


}
