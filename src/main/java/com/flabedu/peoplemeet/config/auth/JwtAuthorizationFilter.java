package com.flabedu.peoplemeet.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        // header 가 있는지 확인
        if ( ! jwtToken.isEmpty() && jwtToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
            // JWT 토큰을 검증해서 정상적인 사용자인지 확인

            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET_KEY)).build().verify(jwtToken);;
            //String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

            String userEmail = decodedJWT.getClaim("useremail").asString();

            System.out.println(userEmail);

            // 서명이 정상적으로 됨
            if (userEmail.isEmpty()) { throw new RuntimeException();}

            User userEntity = userRepository.findUserByEmail(userEmail).get();

            System.out.println(userEntity);

            AuthDetails authDetails = new AuthDetails(userEntity);

            // JTW Token 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(authDetails, null, authDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        chain.doFilter(request, response);
    }
}
