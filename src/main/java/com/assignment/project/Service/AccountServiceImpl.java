package com.assignment.project.Service;

import com.assignment.project.Config.JwtTokenValidator;
import com.assignment.project.Dto.AccountDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.Transaction;
import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserAlreadyExistsException;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Repository.AccountRepo;
import com.assignment.project.Repository.TransactionRepo;
import com.assignment.project.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public Account createAccount(AccountDto accountDTO, User user) throws UserAlreadyExistsException {
        System.out.println("Account DTO before creating account: " + accountDTO);

        if (accountDTO == null) {
            throw new IllegalArgumentException("Account DTO cannot be null");
        }

        if (accountRepo.existsByUser(user)) { // This condition needs to throw the exception
            throw new UserAlreadyExistsException("Account already exists for user: " + user.getEmail());
        }
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setUser(user);
        return accountRepo.save(account);
    }
    public boolean existsByUser(User user) {
        return accountRepo.existsByUser(user);
    }


    @Transactional
    public Account getAccountDetails(Long userId) throws UserNotFoundException {
        return accountRepo.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("Account not found for userId: " + userId));
    }



}
