package com.assignment.project.Repository;

import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByUserId(Long userId);
    public boolean existsByUser(User user);
}
