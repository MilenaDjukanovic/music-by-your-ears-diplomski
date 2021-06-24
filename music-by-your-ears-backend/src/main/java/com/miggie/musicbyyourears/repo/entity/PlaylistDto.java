package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

import java.util.Set;

@Data
public class PlaylistDto {

    /** Playlist ID **/
    private Long id;

    /** Artist **/
    private String artist;

    /** Playlist cover **/
    private byte[] coverImage;
    /** Playlist cover file **/
    private String coverFile;

    /** Playlist audio **/
    private byte[] audio;

    /** Icons of used sounds **/
    private Set<IconsEntity> icons;

    /** Created by user **/
    private UserDto user;

}
