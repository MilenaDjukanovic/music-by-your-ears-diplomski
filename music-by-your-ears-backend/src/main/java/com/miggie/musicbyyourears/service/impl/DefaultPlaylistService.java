package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.IconRepository;
import com.miggie.musicbyyourears.repo.PlaylistRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.*;
import com.miggie.musicbyyourears.requests.CreateIconRequest;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import com.miggie.musicbyyourears.service.AuthorizationService;
import com.miggie.musicbyyourears.service.PlaylistService;
import com.miggie.musicbyyourears.service.mappers.IconCreateMapper;
import com.miggie.musicbyyourears.service.mappers.IconViewMapper;
import com.miggie.musicbyyourears.service.mappers.PlaylistCreateMapper;
import com.miggie.musicbyyourears.service.mappers.PlaylistViewMapper;
import com.miggie.musicbyyourears.service.util.FileCompressorUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultPlaylistService implements PlaylistService {

    /** Playlist Repository **/
    private final PlaylistRepository playlistRepository;

    /** Playlist Create Mapper **/
    private final PlaylistCreateMapper playlistCreateMapper;

    /** Playlist View Mapper **/
    private final PlaylistViewMapper playlistViewMapper;

    /** User Repository **/
    private final UserRepository userRepository;

    /** AuthorizationService **/
    private final AuthorizationService authorizationService;

    /** Icon Repository **/
    private final IconRepository iconRepository;

    /** Icon Create Mapper **/
    private final IconCreateMapper iconCreateMapper;

    /** Icon View Mapper **/
    private final IconViewMapper iconViewMapper;

    @Override
    public PlaylistDto create(CreatePlaylistRequest createPlaylistRequest, CreateIconRequest createIconRequest) {
        Objects.requireNonNull(createPlaylistRequest);
        Objects.requireNonNull(createIconRequest);

        createIconRequest.setImage(FileCompressorUtil.compressBytes(createIconRequest.getImage()));
        IconsEntity iconsEntity = this.iconCreateMapper.toEntity(createIconRequest);
        iconsEntity = this.iconRepository.save(iconsEntity);

        IconsDto iconsDto = this.iconViewMapper.toDto(iconsEntity);
        createPlaylistRequest.setCoverImage(iconsDto);
        //TODO
        createPlaylistRequest.setUserId(1L);
        createPlaylistRequest.setAudio(FileCompressorUtil.compressBytes(createPlaylistRequest.getAudio()));

        PlaylistEntity playlistEntity = this.playlistCreateMapper.toEntity(createPlaylistRequest);

        playlistEntity = this.playlistRepository.save(playlistEntity);

        return this.playlistViewMapper.toDto(playlistEntity);
    }

    @Override
    public Page<PlaylistDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);
        Page<PlaylistEntity> playlists = this.playlistRepository.findAll(pageable);

        this.decompressPlaylists(playlists);

        return playlists.map(this.playlistViewMapper::toDto);
    }

    @Override
    public Page<PlaylistDto> findByUserId(Long id, Pageable pageable) {
        Objects.requireNonNull(id);

        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Playlist with that id can not be found"));

        Page<PlaylistEntity> playlists = this.playlistRepository.findByUser(userEntity, pageable);
        this.decompressPlaylists(playlists);

        return playlists.map(this.playlistViewMapper::toDto);
    }

    @Override
    public void delete(Long playlistId) {
        Objects.requireNonNull(playlistId);

        PlaylistEntity playlistEntity = this.playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist with that id can not be found"));

        if(playlistEntity.getUser().getId().equals(this.authorizationService.getAuthenticatedUser().getId())) {
            this.playlistRepository.deleteById(playlistId);
        }
    }

    private void decompressPlaylists(Page<PlaylistEntity> playlists) {
        playlists.forEach((playlist) -> {
            playlist.setAudio(FileCompressorUtil.decompressBytes(playlist.getAudio()));
            playlist.getCoverImage().setImage(FileCompressorUtil.decompressBytes(playlist.getCoverImage()
                    .getImage()));
        });
    }
}
