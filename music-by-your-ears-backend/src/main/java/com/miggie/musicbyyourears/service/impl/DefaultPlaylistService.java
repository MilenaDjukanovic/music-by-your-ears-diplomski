package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.PlaylistRepository;
import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.repo.entity.PlaylistDto;
import com.miggie.musicbyyourears.repo.entity.PlaylistEntity;
import com.miggie.musicbyyourears.repo.entity.UserEntity;
import com.miggie.musicbyyourears.requests.CreatePlaylistRequest;
import com.miggie.musicbyyourears.service.AuthorizationService;
import com.miggie.musicbyyourears.service.PlaylistService;
import com.miggie.musicbyyourears.service.mappers.PlaylistCreateMapper;
import com.miggie.musicbyyourears.service.mappers.PlaylistViewMapper;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
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

    @Override
    public PlaylistDto create(CreatePlaylistRequest createPlaylistRequest) {
        Objects.requireNonNull(createPlaylistRequest);

        PlaylistEntity playlistEntity = this.playlistCreateMapper.toEntity(createPlaylistRequest);

        playlistEntity = this.playlistRepository.save(playlistEntity);

        return this.playlistViewMapper.toDto(playlistEntity);
    }

    @Override
    public Page<PlaylistDto> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable);

        return this.playlistRepository.findAll(pageable).map(this.playlistViewMapper::toDto);
    }

    @Override
    public Page<PlaylistDto> findByUserId(Long id, Pageable pageable) {
        Objects.requireNonNull(id);

        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Playlist with that id can not be found"));

        return this.playlistRepository.findByUser(userEntity, pageable).map(this.playlistViewMapper::toDto);
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
}
