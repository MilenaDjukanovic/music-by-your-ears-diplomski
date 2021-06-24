package com.miggie.musicbyyourears.repo;

import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Playlist Repository
 *
 * @author mdjukanovic
 */
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    /**
     * Find Playlist by its ID
     * @param id id of the playlist
     * @return optional of Playlist entity
     */
    Optional<PlaylistEntity> findById(Long id);

    /**
     * Finds Playlists for user who created it
     * @param userEntity user entity
     * @return pageable of playlists
     */
    Page<PlaylistEntity> findByUser(UserEntity userEntity, Pageable pageable);

    /**
     * Deletes book by its id
     * @param id id of the book to be deleted
     */
    void deleteById(Long id);
}
