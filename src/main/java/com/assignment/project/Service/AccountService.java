package com.assignment.project.Service;

import com.assignment.project.Dto.AccountDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserAlreadyExistsException;
import com.assignment.project.Exception.UserNotFoundException;

public interface AccountService {
    public Account createAccount(AccountDto accountDto, User user) throws UserAlreadyExistsException;
    public Account getAccountDetails(Long userId) throws UserNotFoundException;
    public boolean existsByUser(User user);
    public boolean isAuthenticated();
}
