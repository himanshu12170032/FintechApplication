package com.assignment.project.Controller;

import com.assignment.project.Dto.TransactionDto;
import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Service.TransactionService;
import com.assignment.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransactionDto transactionDTO,  @RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        User user = userService.findUserByProfile(jwt);
        if(user == null){
            throw new UserNotFoundException("user not found");
        }
        if (!user.getAccount().getId().equals(transactionDTO.getFromAccountId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized: You can only transfer from your own account.");
        }
        try {
            transactionService.processTransaction(transactionDTO);
            return ResponseEntity.ok("Transaction Completed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction Failed: " + e.getMessage());
        }
    }
}
