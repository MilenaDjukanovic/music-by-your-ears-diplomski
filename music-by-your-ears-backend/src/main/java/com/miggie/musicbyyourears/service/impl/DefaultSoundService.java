package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.SoundRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.*;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import com.miggie.musicbyyourears.service.AuthorizationService;
import com.miggie.musicbyyourears.service.SoundService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.IconViewMapper;
import com.miggie.musicbyyourears.service.mappers.SoundCreateMapper;
import com.miggie.musicbyyourears.service.mappers.SoundViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.IconView;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
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

    /** Icon View Mapper **/
    private final IconViewMapper iconViewMapper;

    /** Sound View Mapper **/
    private final SoundViewMapper soundViewMapper;

    /** Sound Create Mapper **/
    private final SoundCreateMapper soundCreateMapper;

    /** Authorization Service **/
    private final AuthorizationService authorizationService;

    @Override
    public SoundDto create(CreateSoundRequest createSoundRequest, CreateIconRequest createIconRequest) {
        Objects.requireNonNull(createIconRequest);
        Objects.requireNonNull(createSoundRequest);

        IconsEntity iconsEntity = this.iconCreateMapper.toEntity(createIconRequest);
        iconsEntity = this.iconRepository.save(iconsEntity);

        IconsDto iconsDto = this.iconViewMapper.toDto(iconsEntity);
        createSoundRequest.setIcon(iconsDto);
        //TODO
        createSoundRequest.setUserId(this.authorizationService.getAuthenticatedUser().getId());

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

        return this.soundRepository.findByUserOrSoundPublic(userEntity, soundPublic, pageable)
                .map(this.soundViewMapper::toDto);
    }
}
