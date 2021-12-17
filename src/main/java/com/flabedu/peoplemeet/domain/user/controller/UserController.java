package com.flabedu.peoplemeet.domain.user.controller;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users/register")
    @ResponseBody
    public String join(@ModelAttribute User user) {
        System.out.println("===============================");
        System.out.println(user.toString());
        System.out.println("===============================");

        user.setRoles("ROLE_USER");
        String rawpassword = user.getPassword();
        String encodedpPassword = bCryptPasswordEncoder.encode(rawpassword);

        user.setPassword(encodedpPassword);

        userRepository.save(user);
        return "joinForm";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

}
