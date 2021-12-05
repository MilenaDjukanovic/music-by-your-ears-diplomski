package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bidirectional mapping between CreatePlaylistRequest and PlaylistEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PlaylistCreateMapper extends EntityMapper<CreatePlaylistRequest, PlaylistEntity> {

    @Mapping(source = "userId", target = "user.id")
    PlaylistEntity toEntity(CreatePlaylistRequest createPlaylistRequest);
}
