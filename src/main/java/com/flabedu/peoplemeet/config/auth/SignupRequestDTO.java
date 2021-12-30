package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    private LocalDateTime dateEmailChecked;

    private String emailToken;

    private String profileImageUrl;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .roles(StringUtils.defaultIfEmpty(this.roles, "USER"))
                .name(this.name)
                .nickName(StringUtils.defaultIfEmpty(this.roles, this.name))
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .birthDay(this.birthDay)
                .profileImageUrl(this.profileImageUrl)
                .build();
    }
}
