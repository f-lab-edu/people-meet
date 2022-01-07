package com.flabedu.peoplemeet.domain.user.repository;

import com.flabedu.peoplemeet.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository 상속하면 자동 컴포넌트 스캔
 * <p>
 * Jpa Query methods
 */
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);

    public User save(User user);
}
