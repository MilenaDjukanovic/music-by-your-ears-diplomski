package com.miggie.musicbyyourears.repo;

import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import com.miggie.musicbyyourears.repo.entity.ReviewsEntity;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Review Repository
 *
 * @author mdjukanovic
 */
public interface ReviewRepository extends JpaRepository<ReviewsEntity, Long> {

    /**
     * Finds review by its id
     * @param id id of the review to find
     * @return optional of Review entity
     */
    Optional<ReviewsEntity> findById(Long id);

    /**
     * Finds reviews for playlists
     * @param playlist Playlist entity
     * @param pageable pageable
     * @return pageable of Playlist entity
     */
    Page<ReviewsEntity> findByPlaylist(PlaylistEntity playlist, Pageable pageable);

    /**
     * Finds reviews by the user who made them
     * @param userEntity User entity
     * @param pageable pageable
     * @return pageable of Playlist entity
     */
    Page<ReviewsEntity> findByUser(UserEntity userEntity, Pageable pageable);

    /**
     * Deletes review by its ID
     *
     * @param id id of the review to delete
     */
    void deleteById(Long id);
}

