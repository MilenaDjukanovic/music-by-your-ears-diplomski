package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.PlaylistRepository;
import com.miggie.musicbyyourears.repo.ReviewRepository;
import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import com.miggie.musicbyyourears.repo.entity.ReviewsDto;
import com.miggie.musicbyyourears.repo.entity.ReviewsEntity;
import com.miggie.musicbyyourears.requests.CreateReviewRequest;
import com.miggie.musicbyyourears.service.PlaylistService;
import com.miggie.musicbyyourears.service.ReviewService;
import com.miggie.musicbyyourears.service.mappers.ReviewViewMapper;
import com.miggie.musicbyyourears.service.mappers.ReviewsCreateMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultReviewsService implements ReviewService {

    /** Review Repository **/
    private final ReviewRepository reviewRepository;

    /** Review Create Mapper **/
    private final ReviewsCreateMapper reviewsCreateMapper;

    /** Review View Mapper **/
    private final ReviewViewMapper reviewViewMapper;

    /** Playlist Repository **/
    private final PlaylistRepository playlistRepository;

    @Override
    public ReviewsDto create(CreateReviewRequest createReviewRequest) {
        ReviewsEntity reviewsEntity = this.reviewsCreateMapper.toEntity(createReviewRequest);

        reviewsEntity = this.reviewRepository.save(reviewsEntity);

        return this.reviewViewMapper.toDto(reviewsEntity);
    }

    @Override
    public Page<ReviewsDto> findByPlaylist(Long playlistId, Pageable pageable) {
        Objects.requireNonNull(playlistId);

        PlaylistEntity playlistEntity = this.playlistRepository.findById(playlistId).
                orElseThrow(() -> new EntityNotFoundException("Playlist for that id can not be found"));

        return this.reviewRepository.findByPlaylist(playlistEntity, pageable).map(this.reviewViewMapper::toDto);
    }

    @Override
    public PlaylistDto findByUser(Long userId) {
        return null;
    }

    @Override
    public void delete(Long reviewId) {

    }
}
