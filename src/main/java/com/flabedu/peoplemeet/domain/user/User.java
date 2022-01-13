package com.flabedu.peoplemeet.domain.user;

import com.flabedu.peoplemeet.domain.BaseEntity;
import com.flabedu.peoplemeet.web.controller.user.dto.UserInfoDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String roles;

    private String name;

    private String nickName;

    private String address;

    private String phoneNumber;

    private String birthDay;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private LocalDateTime emailTokenCreateTime;

    private String emailToken;

    private String profileImageUrl;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public UserInfoDto toUserInfoResponseDto() {
        return UserInfoDto.builder()
                .email(this.email)
                .name(this.name)
                .nickName(this.nickName)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .birthDay(this.birthDay)
                .profileImageUrl(this.profileImageUrl)
                .build()
                ;
    }

    public void encode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
