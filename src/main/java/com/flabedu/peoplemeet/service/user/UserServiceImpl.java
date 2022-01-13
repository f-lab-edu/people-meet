package com.flabedu.peoplemeet.service.user;

import com.flabedu.peoplemeet.common.mail.BaseMailInfo;
import com.flabedu.peoplemeet.common.mail.MailService;
import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.UserStatus;
import com.flabedu.peoplemeet.domain.user.repository.UserMapper;
import com.flabedu.peoplemeet.exception.EmailNotFoundException;
import com.flabedu.peoplemeet.exception.EmailTokenVerifyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final MailService mailService;

    @Override
    @Transactional(readOnly = true)
    public Boolean existEmail(String email) {
        return userMapper.isExistEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        User user = userMapper.selectUserByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException());
        return user;
    }

    @Override
    public void modifyUser(User user) {
        String email = user.getEmail();

        if(!userMapper.isExistEmail(email)) throw new EmailNotFoundException();

        User updateUser = User.builder()
                .email(email)
                .name(user.getName())
                .nickName(user.getNickName())
                .birthDay(user.getBirthDay())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .build();

        userMapper.updateUser(updateUser);
    }

    @Override
    public void deleteUser(String email) {
        if (!userMapper.isExistEmail(email)) throw new EmailNotFoundException();

        userMapper.deleteUser(email);
    }

    @Override
    public void createEmailToken(String email) {
        if (!userMapper.isExistEmail(email)) throw new EmailNotFoundException();

        String uuid = String.valueOf(UUID.randomUUID());

        // SET Email Tempalte 파라미터
        Map<String, String> param = new HashMap<>();
        param.put("EMAIL", email);
        param.put("TOKEN", uuid);

        BaseMailInfo mailInfo = BaseMailInfo.createTemplateMailInfo(email, "PEOPLE-MEET 가입 인증 메일", "template001", param);

        mailService.sendHtmlMail(mailInfo);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("emailToken", uuid);
        paramMap.put("emailTokenCreateTime", LocalDateTime.now());

        userMapper.updateEmailToken(paramMap);
    }

    @Override
    public void verifyEmailToken(String email, String emailToken) {
        User user = userMapper.selectUserByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException());

        if(user.getUserStatus()!=UserStatus.INACTIVE) throw new EmailTokenVerifyException();

        LocalDateTime emailTokenCreateTime = user.getEmailTokenCreateTime();
        Duration duration = Duration.between(emailTokenCreateTime, LocalDateTime.now());

        String savedEmailToken = user.getEmailToken();

        if (!emailToken.equals(savedEmailToken)
                || duration.getSeconds() > (60 * 5)) throw new EmailTokenVerifyException();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("userStatus", UserStatus.ACTIVE);
        userMapper.updateUserStatus(paramMap);
    }
}
