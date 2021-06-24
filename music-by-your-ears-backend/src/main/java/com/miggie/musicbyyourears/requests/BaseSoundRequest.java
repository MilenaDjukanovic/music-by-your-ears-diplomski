package com.miggie.musicbyyourears.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class BaseSoundRequest {

    /** Sound name **/
    @NotBlank
    private String name;

    /** Audio **/
    @NotNull
    private byte[] audio;

    /** Icon for the sound **/
    @NotNull
    private BaseIconRequest iconRequest;

    /** Is the sound public **/
    private boolean soundPublic = false;

    /** Created by user **/
    private Long userId;
}
