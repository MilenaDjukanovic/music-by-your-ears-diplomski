package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

@Data
public class SoundDto {

    /** Sound ID **/
    private Long id;

    /** Name to display **/
    private String nameToShow;

    /** Sound name **/
    private String name;

    /** Sound name **/
    private String audioFile;

    /** Audio **/
    private byte[] audio;

    /** Icon for the sound **/
    private IconsDto icon;

    /** Created by user **/
    private UserDto user;
}
