package com.example.demo.repositorie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser,Integer> {
   Optional<MyUser> findByUsername(String username);
}
