package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

public Optional<User> findByUserEmail(String userEmail);
boolean existsByUserEmail(String userEmail);
boolean existsByPassword(String Password);
}
