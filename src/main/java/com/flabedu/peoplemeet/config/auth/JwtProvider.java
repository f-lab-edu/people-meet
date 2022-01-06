package com.flabedu.peoplemeet.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flabedu.peoplemeet.exception.JWTValidateException;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtProvider {

    @Value("${jwt.token-header-name}")
    private String JWT_HEADER_NAME;

    @Value("${jwt.token-secret-key}")
    private String JWT_SECRET_KEY;

    @Value("${jwt.token-prefix}")
    private String JWT_PREFIX;

    @Value("${jwt.token-expiration-time}")
    private int JWT_EXPIRATION_TIME;

    /**
     * JWT Token 생성
     */
    public String generateJwt(String email) {
        String jwtToken = JWT.create()
                .withSubject("User")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .withClaim("email", email)
                .sign(Algorithm.HMAC256(JWT_SECRET_KEY));

        return JWT_PREFIX + " " + jwtToken;
    }

    /**
     * JWT Token 검증
     */
    public String validateJwt(HttpServletRequest request) {

        String jwtHeader = request.getHeader(JWT_HEADER_NAME);

        if (!jwtHeader.startsWith(JWT_PREFIX)) throw new JWTValidateException();

        String jwtToken = jwtHeader.replace(JWT_PREFIX + " ", "");

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JWT_SECRET_KEY)).build().verify(jwtToken);

        String email = decodedJWT.getClaim("email").asString();

        if (email.isEmpty()) throw new JWTValidateException();

        return email;
    }
}
