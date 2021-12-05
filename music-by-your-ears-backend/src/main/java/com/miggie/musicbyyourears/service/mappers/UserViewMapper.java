package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.UserDto;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.service.mappers.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bidirectional mapping between UserDto and UserEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserViewMapper extends EntityMapper<UserDto, UserEntity> {
}
