package com.web.Bang.repository;

import com.web.Bang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Query(value = "select * \r\n" + "from user \r\n" + "where username like %:name% \r\n"
            + "And role = :role", nativeQuery = true)
    List<User> findByRoleAndUserName(@Param(value = "role") String role, @Param(value = "name") String name);

    @Transactional
    List<User> findByUsernameContaining(String name);

}
