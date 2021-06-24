package com.miggie.musicbyyourears.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class BaseReviewsRequest {

    /** Id of the playlist that was reviewed **/
    @NotNull
    private Long playlistId;

    /** Id of the user that made a review **/
    @NotNull
    private Long userId;

    /** Comment **/
    @NotBlank
    private String comment;
}
