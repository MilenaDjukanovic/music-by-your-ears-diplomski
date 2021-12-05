package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreateUserRequest;
import com.miggie.musicbyyourears.service.mappers.EntityMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bidirectional mapping between CreateUserRequest and UserEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserCreateMapper extends EntityMapper<CreateUserRequest, UserEntity> {
}
