package com.miggie.musicbyyourears.repo;

import com.miggie.musicbyyourears.repo.entity.SoundEntity;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Sound Repository
 *
 * @author mdjukanovic
 */
public interface SoundRepository extends JpaRepository<SoundEntity, Long> {

    /**
     * Finds sound by its id
     * @param id id of the sound to find
     * @return optional of Sound entity
     */
    Optional<SoundEntity> findById(Long id);

    /**
     * Finds sound for a specific user and those which are public
     * @param userEntity
     * @param soundPublic
     * @return
     */
    Page<SoundEntity> findByUserAndSoundPublic(UserEntity userEntity, boolean soundPublic, Pageable pageable);
}
