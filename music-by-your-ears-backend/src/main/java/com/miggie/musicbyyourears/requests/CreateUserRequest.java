package com.miggie.musicbyyourears.requests;

import com.miggie.musicbyyourears.repo.entity.IconsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserRequest extends BaseUserRequest{

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
