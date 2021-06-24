package com.miggie.musicbyyourears.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class BaseIconRequest {

    /** Icon name **/
    @NotBlank
    private String name;

    /** Icon image **/
    @NotNull
    private byte[] image;

    /** Icon image file **/
    @NotBlank
    private String imageFile;
}
