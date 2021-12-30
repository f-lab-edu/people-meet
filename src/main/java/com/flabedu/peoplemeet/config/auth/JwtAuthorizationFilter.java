package com.flabedu.peoplemeet.config.auth;

import com.flabedu.peoplemeet.domain.user.User;
import com.flabedu.peoplemeet.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * 
 * 인증, 인가 필터
 * 
 * OncePerRequestFilter
 * 
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Value("${jwt.token-header-name}")
    private String JWT_HEADER_NAME;

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getHeader(JWT_HEADER_NAME) != null) {
            // JWT 토큰을 검증해서 정상적인 사용자인지 확인
            String email = jwtProvider.validateJwt(request);

            User userEntity = userRepository.findUserByEmail(email).get();

            AuthDetails authDetails = new AuthDetails(userEntity);

            // JTW Token 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(authDetails, null, authDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
