package com.banking.net_banking_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.net_banking_system.model.AccountDetails;
import com.banking.net_banking_system.model.User;
import com.banking.net_banking_system.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerNewUser(User user, String accountType) {
        // 1. Generate unique 12-digit account number
        String accountNumber = generateAccountNumber();

        // 2. Set the User's account type
        user.setAccountType(accountType.toUpperCase()); 

        // 3. Create the Account entity
        AccountDetails account = new AccountDetails();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType.toUpperCase());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus("PENDING");
        account.setUser(user);

        // 4. Link and Save
        user.setAccountDetails(account); 
        return userRepository.save(user);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}