package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.UserStatus;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class SignupRequestDTO {
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String name;

    private String nickName;

    private String address;

    private String phoneNumber;

    private String roles;

    private String birthDay;

    private String profileImageUrl;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickName(StringUtils.defaultIfEmpty(this.nickName, this.name))
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .roles("ROLE_USER")
                .birthDay(this.birthDay)
                .userStatus(UserStatus.INACTIVE)
                .profileImageUrl(this.profileImageUrl)
                .build();
    }
}
