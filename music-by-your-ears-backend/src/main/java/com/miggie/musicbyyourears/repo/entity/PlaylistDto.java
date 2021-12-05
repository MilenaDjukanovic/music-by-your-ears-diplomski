package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

@Data
public class PlaylistDto {

    /** Playlist ID **/
    private Long id;

    /** Name to show **/
    private String nameToShow;
    /** Artist **/
    private String artist;
    /**Playlist name **/
    private String name;
    /** Playlist cover **/
    private IconsDto coverImage;
    /** Playlist audio **/
    private byte[] audio;
    /** Playlist audio file **/
    private String audioFile;

    /** Created by user **/
    private UserDto user;

}
