package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import com.miggie.musicbyyourears.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Public API Endpoint for Playlists
 * @author mdjukanovic
 */
@RestController @RequestMapping("api/playlists")
@AllArgsConstructor
public class PlaylistController {

    /** Playlist Service **/
    private final PlaylistService playlistService;

    @GetMapping("all")
    public Page<PlaylistDto> findAll(Pageable pageable) {
        return this.playlistService.findAll(pageable);
    }

    @GetMapping("")
    public Page<PlaylistDto> findForUser(@RequestBody Long id, Pageable pageable) {
        return this.playlistService.findByUserId(id, pageable);
    }

    @PostMapping()
    public PlaylistDto create(@RequestBody @Valid CreatePlaylistRequest createPlaylistRequest) {
        return this.playlistService.create(createPlaylistRequest);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.playlistService.delete(id);
    }

}
