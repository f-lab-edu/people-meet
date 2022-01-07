package com.flabedu.peoplemeet.service.user;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }
}
