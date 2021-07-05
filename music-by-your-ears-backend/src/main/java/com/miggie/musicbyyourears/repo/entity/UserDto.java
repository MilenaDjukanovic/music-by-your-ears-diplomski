package com.miggie.musicbyyourears.repo.entity;

import lombok.Data;

@Data
public class UserDto {

    /** User id **/
    private Long id;

    /** Username(email) **/
    private String username;
    /** First Name **/
    private String firstName;
    /** Last Name **/
    private String lastName;
    /** Profile image **/
    private IconsDto profileImage;
    /** Description **/
    private String about;
}
