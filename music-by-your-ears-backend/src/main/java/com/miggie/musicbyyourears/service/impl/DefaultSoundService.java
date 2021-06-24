package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.SoundRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.repo.entity.SoundEntity;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.SoundService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.SoundCreateMapper;
import com.miggie.musicbyyourears.service.mappers.SoundViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultSoundService implements SoundService {

    /** Sound Repository **/
    private final SoundRepository soundRepository;

    /** User Repository **/
    private final UserRepository userRepository;

    /** Icon Repository **/
    private final IconRepository iconRepository;

    /** Icon Create Mapper **/
    private final IconCreateMapper iconCreateMapper;

    /** Sound View Mapper **/
    private final SoundViewMapper soundViewMapper;

    /** Sound Create Mapper **/
    private final SoundCreateMapper soundCreateMapper;

    @Override
    public SoundDto create(CreateSoundRequest createSoundRequest) {
        Objects.requireNonNull(createSoundRequest);

        CreateIconRequest createIconRequest = (CreateIconRequest) createSoundRequest.getIconRequest();
        IconsEntity iconsEntity = this.iconRepository.save(this.iconCreateMapper.toEntity(createIconRequest));
        createSoundRequest.setIconRequest(this.iconCreateMapper.toDto(iconsEntity));

        SoundEntity soundEntity = this.soundCreateMapper.toEntity(createSoundRequest);

        soundEntity = this.soundRepository.save(soundEntity);

        return this.soundViewMapper.toDto(soundEntity);
    }

    @Override
    public Page<SoundDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.soundRepository.findAll(pageable).map(this.soundViewMapper::toDto);
    }

    @Override
    public SoundDto findById(Long id) {
        Objects.requireNonNull(id);

        SoundEntity soundEntity = this.soundRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Sound with that id can not be found"));

        return this.soundViewMapper.toDto(soundEntity);
    }

    @Override
    public Page<SoundDto> findByUserAndSoundPublic(Long userId, boolean soundPublic) {
        Objects.requireNonNull(userId);

        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Sound with that id can not be found"));

        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

        return this.soundRepository.findByUserAndSoundPublic(userEntity, soundPublic, pageable)
                .map(this.soundViewMapper::toDto);
    }
}
