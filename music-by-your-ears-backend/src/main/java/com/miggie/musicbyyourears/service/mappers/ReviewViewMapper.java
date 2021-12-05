package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.ReviewsDto;
import com.miggie.musicbyyourears.repo.entity.ReviewsEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

/**
 * Mapper used for bidirectional mapping between ReviewsDto and ReviewsEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewViewMapper extends EntityMapper<ReviewsDto, ReviewsEntity> {
}
