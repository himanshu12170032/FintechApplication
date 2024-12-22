package com.assignment.project.Controller;

import com.assignment.project.Dto.AccountDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Service.AccountService;
import com.assignment.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDTO, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByProfile(jwt);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (accountService.existsByUser(user)) {
            throw new Exception("User has already created account");
        }
        Account account = accountService.createAccount(accountDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long id, @RequestHeader("Authorization")  String jwt) throws UserNotFoundException {
        User user = userService.findUserByProfile(jwt);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Account account = accountService.getAccountDetails(id);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Account not found
        }

        if (!account.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // User does not own this account
        }
        return ResponseEntity.ok(account);
    }
}
