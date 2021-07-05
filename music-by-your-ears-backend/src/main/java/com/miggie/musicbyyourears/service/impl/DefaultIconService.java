package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.service.IconService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.IconViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultIconService implements IconService {

    /** Icon Repository **/
    private final IconRepository iconRepository;

    /** Icon View Mapper **/
    private final IconViewMapper iconViewMapper;

    /** Icon Create Mapper **/
    private final IconCreateMapper iconCreateMapper;

    @Override
    public IconsDto create(CreateIconRequest createIconRequest) {
        Objects.requireNonNull(createIconRequest);

        IconsEntity iconsEntity = this.iconCreateMapper.toEntity(createIconRequest);

        iconsEntity = this.iconRepository.save(iconsEntity);

        return this.iconViewMapper.toDto(iconsEntity);
    }

    @Override
    public Page<IconsDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.iconRepository.findAll(pageable).map(this.iconViewMapper::toDto);
    }

    @Override
    public IconsDto findById(Long id) {
        Objects.requireNonNull(id);

        IconsEntity iconsEntity = this.iconRepository.findById(id).orElse(null);

        return this.iconViewMapper.toDto(iconsEntity);
    }

    @Override
    public Page<IconsDto> findByExtension(String extension, Pageable pageable) {
       Objects.requireNonNull(extension);

       return this.iconRepository.findByExtension(extension, pageable).map(this.iconViewMapper::toDto);
    }
}
