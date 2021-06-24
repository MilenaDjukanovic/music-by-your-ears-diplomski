package com.miggie.musicbyyourears.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
    private String about;
}
