package com.flabedu.peoplemeet.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flabedu.peoplemeet.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * UsernamePasswordAuthenticationFilter
 * <p>
 * 로그인 요청 시 로그인 처리 하는 스프링 시큐리티 필터
 * <p>
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final private AuthenticationManager authenticationManager;

    /**
     * 로그인 요청 메소드
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {
            user = objectMapper.readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
//          에러처리
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // AuthDetailseService.loadUserByUsername() 호출 된 후 Authentication이 리턴된다.
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        AuthDetails authDetails = (AuthDetails)authentication.getPrincipal();
        System.out.println("로그인 완료됨 : " + authDetails.getUser().toString());

        return authentication;
    }

    /**
     * 로그인 성공
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다.");
        AuthDetails authDetails = (AuthDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("User")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("useremail", authDetails.getUserEmail())
                .sign(Algorithm.HMAC256(JwtProperties.SECRET_KEY));
        System.out.println(jwtToken);


        response.addHeader("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
