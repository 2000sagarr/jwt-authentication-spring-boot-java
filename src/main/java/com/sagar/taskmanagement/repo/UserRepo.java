package com.sagar.taskmanagement.repo;

import com.sagar.taskmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
