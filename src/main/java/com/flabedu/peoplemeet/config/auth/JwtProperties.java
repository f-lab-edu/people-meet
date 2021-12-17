package com.flabedu.peoplemeet.config.auth;

public interface JwtProperties {
    String SECRET_KEY = "peoplemeet";
    int EXPIRATION_TIME = 60000 * 30;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
