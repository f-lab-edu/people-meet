package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserMapper;
import com.flabedu.peoplemeet.exception.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService
 * <p>
 * 스프링 시큐리티에서 유저의 정보를 가져오는 서비스
 * <p>
 * 참조 : https://user-images.githubusercontent.com/79847020/146328873-7796773d-a2a4-4484-bd80-bc22051892c4.png
 */
@Service
public class AuthDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userMapper.selectUserByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException());
        return new AuthDetails(user);
    }
}