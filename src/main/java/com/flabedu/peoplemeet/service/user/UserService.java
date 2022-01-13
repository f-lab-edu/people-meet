package com.flabedu.peoplemeet.service.user;

import com.flabedu.peoplemeet.domain.user.User;

public interface UserService {
    public Boolean existEmail(String email);

    public User findUserByEmail(String email);

    public void modifyUser(User user);

    public void deleteUser(String email);

    void createEmailToken(String emailToken);

    void verifyEmailToken(String email, String emailToken);
}
