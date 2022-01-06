package com.flabedu.peoplemeet.service.user;

import com.flabedu.peoplemeet.domain.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
}
