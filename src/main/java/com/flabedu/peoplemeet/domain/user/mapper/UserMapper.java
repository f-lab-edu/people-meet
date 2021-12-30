package com.flabedu.peoplemeet.domain.user.mapper;

import com.flabedu.peoplemeet.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> selectUserByEmail(String email);

    boolean isExistEmail(String email);

    void insertUser(User user);
}



