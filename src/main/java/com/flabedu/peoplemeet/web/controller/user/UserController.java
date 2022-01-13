package com.flabedu.peoplemeet.web.controller.user;

import com.flabedu.peoplemeet.config.auth.AuthDetails;
import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.service.user.UserService;
import com.flabedu.peoplemeet.web.controller.user.dto.UserEmailDto;
import com.flabedu.peoplemeet.web.controller.user.dto.UserEmailTokenDto;
import com.flabedu.peoplemeet.web.controller.user.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 유저 중복 확인
     */
    @RequestMapping(method = RequestMethod.GET, value = "/duplicate")
    @ResponseBody
    public ResponseEntity isDuplicateUser(@RequestBody @Valid UserEmailDto requestDto) {
        String email = requestDto.getEmail();

        ResponseEntity response;

        if (userService.existEmail(email))
            response = ResponseEntity.notFound().build();
        else
            response = ResponseEntity.ok().build();

        return response;
    }

    /**
     * 유저 정보 조회
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserInfoDto> findUser(@AuthenticationPrincipal AuthDetails authDetails) {
        User user = userService.findUserByEmail(authDetails.getEmail());

        UserInfoDto findUserInfoDto = user.toUserInfoResponseDto();

        return ResponseEntity.ok(findUserInfoDto);
    }

    /**
     * 유저 정보 수정
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity modifyUser(@RequestBody UserInfoDto requestDto, @AuthenticationPrincipal AuthDetails authDetails) {

        User user = requestDto.toEntity();

        userService.modifyUser(user);

        return ResponseEntity.ok().build();
    }

    /**
     * 유저 탈퇴
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteUser(@AuthenticationPrincipal AuthDetails authDetails) {
        String email = authDetails.getEmail();

        userService.deleteUser(email);

        return ResponseEntity.ok().build();
    }

    /**
     * 이메일 인증 토큰 발행
     */
    @RequestMapping(method = RequestMethod.POST, value = "/token")
    @ResponseBody
    public ResponseEntity createEmailToken(@AuthenticationPrincipal AuthDetails authDetails) {
        String email = authDetails.getEmail();

        userService.createEmailToken(email);

        return ResponseEntity.ok().build();
    }

    /**
     * 이메일 인증 토큰 검증
     */
    @RequestMapping(method = RequestMethod.GET, value = "/token")
    @ResponseBody
    public ResponseEntity verifyEmailToken(@ModelAttribute UserEmailTokenDto requestDTO) {
        String email = requestDTO.getEmail();
        String emailToken = requestDTO.getToken();

        userService.verifyEmailToken(email, emailToken);

        return ResponseEntity.ok("인증이 완료 되었습니다.");
    }
}
