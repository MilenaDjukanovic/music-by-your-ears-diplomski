package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

@Data
public class IconsDto {

    /** Icon ID **/
    private Long id;

    /** Icon extension **/
    private String extension;

    /** Icon name **/
    private String name;

    /** Icon image **/
    private byte[] image;

    /** Icon image file **/
    private String imageFile;
}
