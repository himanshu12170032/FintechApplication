package com.assignment.project.Repository;

import com.assignment.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    public boolean existsByEmail(String email);
    User findByEmail(String email);
}
