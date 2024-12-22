package com.assignment.project.Service;

import com.assignment.project.Dto.TransactionDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.Transaction;
import com.assignment.project.Repository.AccountRepo;
import com.assignment.project.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {

    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private TransactionRepo transactionRepository;

    @Async
    public CompletableFuture<Transaction> processTransaction(TransactionDto transactionDTO) {
        Account fromAccount = accountRepository.findById(transactionDTO.getFromAccountId()).orElseThrow();
        Account toAccount = accountRepository.findById(transactionDTO.getToAccountId()).orElseThrow();

        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Insufficient funds.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - transactionDTO.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transactionDTO.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDate(new Date());
        transaction.setType("TRANSFER");
        transaction.setStatus("COMPLETED");
        transaction.setAccount(fromAccount);

        transactionRepository.save(transaction);

        return CompletableFuture.completedFuture(transaction);
    }
}
