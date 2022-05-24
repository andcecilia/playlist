package com.letscode.domain.repository;

import com.letscode.domain.model.Music;
import com.letscode.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findAllByUserEntityEquals(User userEntity);
}
