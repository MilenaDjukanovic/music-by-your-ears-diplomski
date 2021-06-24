package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between PlaylistDto and PlaylistEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PlaylistViewMapper extends EntityMapper<PlaylistDto, PlaylistEntity> {
}
