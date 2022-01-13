package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserMapper;
import com.flabedu.peoplemeet.exception.EmailDuplicatedException;
import com.flabedu.peoplemeet.exception.message.UserErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * AuthController
 *
 * 인증 컨트롤러
 */
@RestController
public class AuthController {

    @Value("${jwt.token-header-name}")
    private String JWT_HEADER_NAME;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid final SigninRequestDTO request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        // 비밀번호 체크 및 loadUserByUsername() 호출
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();

        String jwtToken = jwtProvider.generateJwt(authDetails.getEmail());

        return ResponseEntity.ok().header(JWT_HEADER_NAME, jwtToken).build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid final SignupRequestDTO request) {

        String email = request.getEmail();

        if (userMapper.isExistEmail(email))
            throw new EmailDuplicatedException();

        User user = request.toEntity();
        user.encode(bCryptPasswordEncoder);

        userMapper.insertUser(user);

        return ResponseEntity.ok().build();
    }
}
