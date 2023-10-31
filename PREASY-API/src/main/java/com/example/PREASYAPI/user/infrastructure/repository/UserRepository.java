package com.example.PREASYAPI.user.infrastructure.repository;

import com.example.PREASYAPI.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String username);
}