package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.repo.entity.SoundEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bidirectional mapping between SoundDto and SoundEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SoundViewMapper extends EntityMapper<SoundDto, SoundEntity>{
}
