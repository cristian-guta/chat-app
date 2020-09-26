package com.chatapp.demo.repository;

import com.chatapp.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);

    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);
}
