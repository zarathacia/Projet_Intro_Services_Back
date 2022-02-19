package com.example.services.repository;

import com.example.services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByFullName(String username);

    User findUserByEmail(String email);
}
