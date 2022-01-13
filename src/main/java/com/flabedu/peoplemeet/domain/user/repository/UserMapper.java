package com.flabedu.peoplemeet.domain.user.repository;

import com.flabedu.peoplemeet.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> selectUserByEmail(String email);

    boolean isExistEmail(String email);

    void insertUser(User user);

    void updateUser(User user);

    void updateUserStatus(Map paramMap);

    void deleteUser(String email);

    void updateEmailToken(Map paramMap);
}



