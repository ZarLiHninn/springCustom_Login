package com.example.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUsername(String username);
}
