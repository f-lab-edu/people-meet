package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserDeatils
 * <p>
 * 스프링 시큐리티에서 사용자의 정보를 담는 인터페이스
 */
@Data
public class AuthDetails implements UserDetails {

    private User user;

    public AuthDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoleList().forEach(r -> {
            authorities.add(() -> r);
        });
        return authorities;
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getUsername() {
        return this.getEmail(); //email을 User Id 로 사용
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
