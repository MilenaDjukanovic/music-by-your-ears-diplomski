package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between CreateIconRequest and IconEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface IconCreateMapper extends EntityMapper<CreateIconRequest, IconsEntity>{
}
