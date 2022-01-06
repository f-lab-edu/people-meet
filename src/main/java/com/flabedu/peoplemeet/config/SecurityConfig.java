package com.flabedu.peoplemeet.config;

import com.flabedu.peoplemeet.config.auth.JwtAuthorizationFilter;
import com.flabedu.peoplemeet.config.auth.JwtProvider;
import com.flabedu.peoplemeet.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Securty 설정 파일
 *
 * 참고 : SecurityContextHodler 스프링 시큐리티 인메모리 세션 저장소
 * SecurityContextHodler <- Authentication객체 <- UserDetailService 객체 <- User 객체
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManagerBean(), userRepository, jwtProvider());
    }

    /**
     * 스프링 시큐리티 권한 설정
     * <p>
     * authorizeRequests() : 요청에 대한 권한을 지정한 객체를 반환
     * anyRequest().authenticated() : 인증이 되어야 한다는 이야기이다.
     * anonymous() : 인증되지 않은 사용자가 접근할 수 있습니다.
     * authenticated() 인증된 사용자만 접근할 수 있습니다.
     * fullyAuthenticated() : 완전히 인증된 사용자만 접근할 수 있다.
     * hasRole() or hasAnyRole() : 특정 권한을 가지는 사용자만 접근할 수 있습니다.
     * hasAuthority() or hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있습니다.
     * hasIpAddress() : 특정 아이피 주소를 가지는 사용자만 접근할 수 있습니다.
     * access() : SpEL? 표현식에 의한 결과에 따라 접근할 수 있다.
     * not() : 접근 제한 기능을 해제
     * permitAll() or denyAll() : 접근을 전부 허용하거나 제한한다.
     * rememberMe() : 리멤버 기능을 통해 로그인한 사용자만 접근할 수 있습니다.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); // csrf 보안 disable

        http.formLogin().disable()
                .httpBasic().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(jwtAuthorizationFilter());

        http.authorizeRequests()
                .antMatchers("/signin", "/signup")
                .permitAll()
//                .antMatchers("/users/**")
//                .access("hasRole('USER')")
                .anyRequest()
/* 현재는 모든 요청 허용. */
/*.authenticated()*/
                .permitAll()
        ;
    }
}
