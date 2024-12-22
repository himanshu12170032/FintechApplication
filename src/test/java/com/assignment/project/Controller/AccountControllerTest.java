//package com.assignment.project.Controller;
//
//import com.assignment.project.Dto.AccountDto;
//import com.assignment.project.Entity.Account;
//import com.assignment.project.Service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import static org.mockito.Mockito.*;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class AccountControllerTest {
//    @Mock
//    private AccountService accountService;
//
//    @InjectMocks
//    private AccountController accountController;
//
//    private AccountDto accountDto;
//
//    @BeforeEach
//    void setUp() {
//       MockitoAnnotations.openMocks(this);
//        accountDto = new AccountDto();
//        accountDto.setBalance(1000.0);
//    }
//
//    @Test
//    void testCreateAccount_ValidInput() {
//        // Arrange
//        Account account = new Account();
//        account.setId(1L);
//        account.setBalance(1000.0);
//        when(accountService.createAccount(any(AccountDto.class))).thenReturn(account);
//
//        // Act
//        ResponseEntity<Account> response = accountController.createAccount(accountDto, "Bearer token");
//
//        // Assert
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(1000.0, response.getBody().getBalance());
//    }
//
//    @Test
//    void testCreateAccount_InvalidInput() {
//        // Arrange
//        AccountDto invalidAccountDto = new AccountDto();  // Missing required fields
//
//        // Act
//        ResponseEntity<Account> response = accountController.createAccount(invalidAccountDto, "Bearer token");
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//}