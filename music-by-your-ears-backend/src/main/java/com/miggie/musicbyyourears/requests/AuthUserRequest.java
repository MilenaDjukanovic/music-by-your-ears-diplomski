package com.miggie.musicbyyourears.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUserRequest extends BaseUserRequest{

    /** Username **/
    @NotBlank @Email
    private String username;

    /** Password **/
    @NotBlank
    private String password;
}
