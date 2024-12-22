package com.assignment.project.Service;

import com.assignment.project.Dto.AccountDto;
import com.assignment.project.Entity.Account;
import com.assignment.project.Entity.User;
import com.assignment.project.Exception.UserAlreadyExistsException;
import com.assignment.project.Exception.UserNotFoundException;
import com.assignment.project.Repository.AccountRepo;
import com.assignment.project.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepo accountRepository;

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private User user;
    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        account = new Account();
        account.setId(1L);
        account.setBalance(1000.0);
        account.setUser(user);
    }
    // Test createAccount with valid input
    @Test
    void testCreateAccount_Valid() throws UserAlreadyExistsException {

        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(accountRepository.existsByUser(user)).thenReturn(false);

        Account createdAccount = accountService.createAccount(new AccountDto(1000.0), user);

        assertNotNull(createdAccount);
        assertEquals(1000.0, createdAccount.getBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
    }


    // Test createAccount when user already exists
    @Test
    void testCreateAccount_UserAlreadyExists() throws UserAlreadyExistsException {
        when(accountRepository.existsByUser(user)).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            accountService.createAccount(new AccountDto(1000.0), user);
        });
        assertEquals("Account already exists for user: " + user.getEmail(), exception.getMessage());
    }


    // 3. Test getAccountDetails with valid user ID
    @Test
    void testGetAccountDetails_ValidUser() throws UserNotFoundException {
        when(accountRepository.findByUserId(anyLong())).thenReturn(Optional.of(account));
        Account retrievedAccount = accountService.getAccountDetails(1L);
        assertNotNull(retrievedAccount);
        assertEquals(1L, retrievedAccount.getId());
        assertEquals(1000.0, retrievedAccount.getBalance());
    }

    // 4. Test getAccountDetails with invalid user ID
    @Test
    void testGetAccountDetails_InvalidUser() throws UserNotFoundException {
        when(accountRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            accountService.getAccountDetails(999L); // Act with invalid userId
        });

        assertEquals("Account not found for userId: 999", exception.getMessage());
    }



    // 5. Test existsByUser when account exists for a user
    @Test
    void testExistsByUser_AccountExists() {
        when(accountRepository.existsByUser(user)).thenReturn(true);
        boolean exists = accountService.existsByUser(user);
        assertTrue(exists);
    }

    // 6. Test existsByUser when account does not exist for a user
    @Test
    void testExistsByUser_AccountDoesNotExist() {
        when(accountRepository.existsByUser(user)).thenReturn(false);
        boolean exists = accountService.existsByUser(user);
        assertFalse(exists);
    }
}