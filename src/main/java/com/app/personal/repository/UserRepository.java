package com.app.personal.repository;

import com.app.personal.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

    @Query(value = "SELECT u FROM UserInfo u WHERE u.id = 2 AND u.name = 'test'", nativeQuery = true)
    UserInfo getUserByFirstNameAndId();
}
