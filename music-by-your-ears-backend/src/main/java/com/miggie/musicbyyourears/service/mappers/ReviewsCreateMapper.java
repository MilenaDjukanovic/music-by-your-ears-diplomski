package com.miggie.musicbyyourears.service.mappers;

import com.miggie.musicbyyourears.repo.entity.ReviewsEntity;
import com.miggie.musicbyyourears.requests.CreateReviewRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used for bidirectional mapping between CreateReviewRequest and ReviewsEntity
 *
 * @author mdjukanovic
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewsCreateMapper extends EntityMapper<CreateReviewRequest, ReviewsEntity>{

    @Mapping(source = "playlistId", target = "playlist.id")
    @Mapping(source = "userId", target = "user.id")
    ReviewsEntity toEntity(CreateReviewRequest createReviewRequest);
}
