package com.miggie.musicbyyourears.requests;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserRequest extends BaseUserRequest{

    /** Username **/
    @NotBlank @Email
    private String username;

    /** Password **/
    @NotBlank
    private String password;

    /** Repeated password (for confirmation) **/
    @NotBlank
    private String rePassword;

    /** First Name **/
    @NotBlank
    private String firstName;

    /** Last Name **/
    @NotBlank
    private String lastName;

    /** Description **/
    @NotBlank
    private String about;

    /** Profile image **/
    private IconsDto profileImage;
}
