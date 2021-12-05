package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.ReviewsDto;
import com.miggie.musicbyyourears.requests.CreateReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for review related operations
 *
 * @author mdjukanovic
 */
public interface ReviewService {

    /**
     * Creates review from the createReviewRequest
     * @param createReviewRequest request for review creation containing review details
     * @return Review object for viewing if successful
     */
    ReviewsDto create(CreateReviewRequest createReviewRequest);

    /**
     * Finds all reviews for playlist
     * @param playlistId id of the playlist
     * @return pageable of reviews
     */
    Page<ReviewsDto> findByPlaylist(Long playlistId, Pageable pageable);

    /**
     * Deletes review entity
     *
     * @param reviewId id of the review to be deleted
     */
    void delete(Long reviewId);
}
