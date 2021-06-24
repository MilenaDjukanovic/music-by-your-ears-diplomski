package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.SoundEntity;
import com.miggie.musicbyyourears.requests.CreateSoundRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bi directional mapping between CreateSoundRequest and SoundEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SoundCreateMapper extends EntityMapper<CreateSoundRequest, SoundEntity> {

    @Mapping(source = "userId", target = "user.id")
    SoundEntity toEntity(CreateSoundRequest createSoundRequest);
}
