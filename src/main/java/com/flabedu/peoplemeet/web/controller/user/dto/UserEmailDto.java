package com.flabedu.peoplemeet.web.controller.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserEmailDto {
    @Email
    String email;
}
