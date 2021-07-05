package com.miggie.musicbyyourears.service;

import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for playlist related operations
 *
 * @author mdjukanovic
 */
public interface PlaylistService {

    /**
     * Creates playlists from the createPlaylistRequest
     * @param createPlaylistRequest request for playlist creation containing playlist details
     * @return Playlist object for viewing if successful
     */
    PlaylistDto create(CreatePlaylistRequest createPlaylistRequest, CreateIconRequest createIconRequest);

    /**
     * Finds all playlists
     * @param pageable pageable
     * @return pageable of playlists
     */
    Page<PlaylistDto> findAll(Pageable pageable);

    /**
     * Finds playlist by id
     * @param id id of the playlist
     * @return playlistDto if successfully found
     */
    Page<PlaylistDto> findByUserId(Long id, Pageable pageable);

    /**
     * Deletes playlist entity
     *
     * @param playlistId id of the playlist to be deleted
     */
    void delete(Long playlistId);
}
