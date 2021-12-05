package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.SoundDto;

import java.util.List;

/**
 * Service for mixing multiple sound files into one
 *
 * @author mdjukanovic
 */
public interface SoundMixingService{

    SoundDto mixSounds(List<SoundDto> sounds);
}
