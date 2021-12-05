package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service for sound related operations
 *
 * @author mdjukanovic
 */
public interface SoundService {

    /**
     * Creates sounds from the createSoundRequest
     * @param createSoundRequest request for sound creation containing sound details
     * @return Sound object for viewing if successful
     */
    SoundDto create(CreateSoundRequest createSoundRequest, CreateIconRequest createIconRequest);

    /**
     * Finds all sounds
     * @param pageable pageable
     * @return pageable of sounds
     */
    Page<SoundDto> findAll(Pageable pageable);

    /**
     * Finds sound by id
     * @param id id of the sound
     * @return soundDto if successfully found
     */
    SoundDto findById(Long id);

    /**
     * Finds sound for a specific user and those which are public
     * @param userId userEntity
     * @param soundPublic flag if the sound is public or not
     * @return soundDto
     */
    Page<SoundDto> findByUserAndSoundPublic(Long userId, boolean soundPublic);

    /**
     * Finds sound for a specific user
     * @return page os soundDto
     */
    Page<SoundDto> findByUser();


    List<SoundDto> getSoundsForId(List<Long> soundId);
}
