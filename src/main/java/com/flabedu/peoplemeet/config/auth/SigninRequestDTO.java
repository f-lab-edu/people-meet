package com.flabedu.peoplemeet.config.auth;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class SigninRequestDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;
}


