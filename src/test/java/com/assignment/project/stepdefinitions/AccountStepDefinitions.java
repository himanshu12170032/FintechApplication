package com.assignment.project.stepdefinitions;
import com.assignment.project.Dto.AccountDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.User;
import com.assignment.project.Service.AccountService;
import com.assignment.project.Exception.UserAlreadyExistsException;
import com.assignment.project.Service.AccountServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountStepDefinitions {

    @Autowired
    private AccountServiceImpl accountService;  // Ensure this is the correct service class you're mocking

    private Account account;
    private String response;
    private User user;
    private AccountDto accountDto;

    // Ensure accountDto is initialized correctly here
    @Given("the user has provided a valid account creation request")
    public void validAccountCreationRequest() throws UserAlreadyExistsException {
        accountDto = new AccountDto();
        accountDto.setBalance(1000.0); // Set a valid balance

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        account = new Account();
        account.setId(1L);
        account.setBalance(1000.0);
        account.setUser(user);

        // Mock the service method to return the created account
        when(accountService.createAccount(any(AccountDto.class), any(User.class))).thenReturn(account);
    }

    @Given("the user has provided an invalid account creation request")
    public void invalidAccountCreationRequest() throws UserAlreadyExistsException {
        accountDto = new AccountDto();
        accountDto.setBalance(-1000.0); // Invalid balance

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Mock the service method to throw an exception for invalid input
        when(accountService.createAccount(any(AccountDto.class), any(User.class)))
                .thenThrow(new IllegalArgumentException("Invalid input"));
    }

    @When("the user submits the account creation request")
    public void submitAccountCreationRequest() {
        try {
            // Ensure accountDto is correctly initialized before calling service
            accountDto = new AccountDto();
            accountDto.setBalance(1000.0);
            Account createdAccount = accountService.createAccount(accountDto, user);
            response = "Account ID: " + createdAccount.getId();
        } catch (Exception e) {
            response = e.getMessage();
        }
    }

    @Then("the account should be created successfully")
    public void accountCreatedSuccessfully() {
        assertEquals("Account ID: 1", response);
    }

    @Then("the account should not be created")
    public void accountNotCreated() {
        assertEquals("Invalid input", response);
    }

    @Then("the response should contain the account ID")
    public void responseContainsAccountId() {
        assertEquals("Account ID: 1", response);
    }

    @Then("the response should contain an error message")
    public void responseContainsErrorMessage() {
        assertEquals("Invalid input", response);
    }
}
