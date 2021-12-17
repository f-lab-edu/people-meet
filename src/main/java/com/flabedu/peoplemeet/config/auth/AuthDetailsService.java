package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * UserDetailsService
 *
 * 스프링 시큐리티에서 유저의 정보를 가져오는 인터페이스
 *
 * 참조 : https://user-images.githubusercontent.com/79847020/146328873-7796773d-a2a4-4484-bd80-bc22051892c4.png
 */
@Service
public class AuthDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException(userEmail));

        return new AuthDetails(user);
    }
}