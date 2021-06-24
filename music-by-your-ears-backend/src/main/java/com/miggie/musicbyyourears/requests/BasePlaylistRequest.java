package com.miggie.musicbyyourears.requests;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public abstract class BasePlaylistRequest {

    /** Artist **/
    @NotBlank
    private String artist;

    /** Playlist cover **/
    @NotNull
    private byte[] coverImage;

    /** Playlist cover file **/
    @NotBlank
    private String coverFile;

    /** Playlist audio **/
    @NotNull
    private byte[] audio;

    /** Icons ID of used sounds **/
    private Set<IconsDto> icons;

    /** Added by user **/
    @NotNull
    private Long userId;


}
