package com.flabedu.peoplemeet.web.controller.user.dto;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class UserInfoDto {
    @Email
    @NotNull
    private String email;

    private String name;

    private String nickName;

    private String address;

    private String phoneNumber;

    private String birthDay;

    private UserStatus userStatus;

    private String profileImageUrl;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .nickName(this.nickName)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .birthDay(this.birthDay)
                .profileImageUrl(this.profileImageUrl)
                .build();
    }
}

