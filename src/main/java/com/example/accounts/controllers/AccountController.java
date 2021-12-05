package com.example.accounts.controllers;

import com.example.accounts.models.Account;
import com.example.accounts.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class AccountController {
    private final AccountRepository accountRepository;

    @PostMapping("/accounts/new")
    Account newAccount(@RequestBody Account account) {
        if(accountRepository.existsById(account.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una cuenta con ese username. Creación de cuenta declinada.");
        }
        if(account.getBalance() >= 0){
            account.setLastChange(new Date());            
        }else{
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance invalido. Creación de cuenta declinada.");            
        }
        return accountRepository.save(account);
    }

    @GetMapping("/accounts/all")
    List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username) {
        return accountRepository.findById(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontró al usuario " + username));
    }

    @PutMapping("/accounts/{username}")
    Account updateAccount(@PathVariable String username, @RequestBody Account account) {
        Account userAccount = accountRepository.findById(username).orElse(null);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay cuenta con el usuario " + username);
        }
        if (userAccount.getBalance() != account.getBalance()) {
            userAccount.setBalance(account.getBalance());
        }
        if (account.getUsername() != null) {
            if (!account.getUsername().isEmpty() && (userAccount.getUsername() != account.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede asignar la cuenta a otro usuario");
            }
        }
        userAccount.setLastChange(new Date());
        accountRepository.save(userAccount);
        return userAccount;
    }

    @DeleteMapping("/accounts/{username}")
    Account deleteAccount(@PathVariable String username) {
        Account userAccount = accountRepository.findById(username).orElse(null);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay cuenta con el usuario " + username);
        }
        if (userAccount.getBalance() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No podemos borrar esta cuenta porque aún tiene saldo");
        }
        accountRepository.delete(userAccount);
        return userAccount;
    }
}
