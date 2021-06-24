package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

@Data
public class ReviewsDto {

    /** Review ID **/
    private Long id;

    /** Playlist that was commented **/
    private PlaylistDto playlist;

    /** User which made the comment **/
    private UserDto user;

    /** Comment **/
    private String comment;
}

