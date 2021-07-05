package com.miggie.musicbyyourears.requests;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import com.miggie.musicbyyourears.repo.entity.IconsEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public abstract class BasePlaylistRequest {

    /** Name to show **/
    @NotBlank
    private String nameToShow;

    /** Artist **/
    @NotBlank
    private String artist;

    /** Playlist name **/
    @NotBlank
    private String name;

    /** Playlist cover **/
    @NotNull
    private IconsDto coverImage;

    /** Playlist audio **/
    @NotNull
    private byte[] audio;

    /** Playlist audio file **/
    @NotBlank
    private String audioFile;

    /** Added by user **/
    private Long userId;


}
