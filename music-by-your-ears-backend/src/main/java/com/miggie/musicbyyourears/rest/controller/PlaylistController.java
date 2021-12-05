package com.miggie.musicbyyourears.rest.controller;

import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import com.miggie.musicbyyourears.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public Page<PlaylistDto> findAll(
            Pageable pageable) {
        return this.playlistService.findAll(pageable);
    }

    @GetMapping("/{userId}")
    public Page<PlaylistDto> findForUser(@PathVariable Long userId, Pageable pageable) {
        return this.playlistService.findByUserId(userId, pageable);
    }

    @PostMapping()
    public PlaylistDto create(@RequestParam("imageFile") MultipartFile imageFile,
                              @RequestParam("audioFile") MultipartFile audioFile,
                              @RequestParam("nameToShow") String nameToShow,
                              @RequestParam("artist") String artist,
                              @RequestParam("extension") String extension) throws IOException {
        CreateIconRequest createIconRequest = new CreateIconRequest();
        createIconRequest.setName(imageFile.getOriginalFilename());
        createIconRequest.setImageFile(imageFile.getContentType());
        createIconRequest.setImage(imageFile.getBytes());
        createIconRequest.setExtension(extension);

        CreatePlaylistRequest createPlaylistRequest = new CreatePlaylistRequest();
        createPlaylistRequest.setName(audioFile.getOriginalFilename());
        createPlaylistRequest.setAudio(audioFile.getBytes());
        createPlaylistRequest.setAudioFile(audioFile.getContentType());
        createPlaylistRequest.setNameToShow(nameToShow);
        createPlaylistRequest.setArtist(artist);

        return this.playlistService.create(createPlaylistRequest, createIconRequest);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.playlistService.delete(id);
    }

}
