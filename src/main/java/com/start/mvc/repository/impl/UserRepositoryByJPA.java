package com.start.mvc.repository.impl;

import com.start.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryByJPA  extends JpaRepository<User, Long> {

    User findByUserName(String nickName);

    boolean existsByEmail(String email);

    boolean existsByUserName(String nickName);

}
