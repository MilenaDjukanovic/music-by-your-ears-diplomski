package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bi directional mapping between IconsDto and IconsEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder =  @Builder(disableBuilder = true))
public interface IconViewMapper extends EntityMapper<IconsDto, IconsEntity>{
}
