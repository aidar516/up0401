package com.example.demo.repository;

import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long>{
    User findByUsername(String username);
}
