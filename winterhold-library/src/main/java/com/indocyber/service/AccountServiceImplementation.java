package com.indocyber.service;

import com.indocyber.ApplicationUserDetails;
import com.indocyber.dto.RegisterDto;
import com.indocyber.dto.account.RegisterDTO;
import com.indocyber.entity.Account;
import com.indocyber.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService, UserDetailsService {
    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register Account
    @Override
    public void registerAccount(RegisterDto dto) {
        String hashPassword = passwordEncoder.encode(dto.getPassword());

        Account account = new Account(
                dto.getUsername(),
                hashPassword
        );

        // save/register new account
        accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findById(username);

        Account tempAccount = null;
        if(account.isPresent()){
            tempAccount = account.get();
        }

        return new ApplicationUserDetails(tempAccount);
    }

}
