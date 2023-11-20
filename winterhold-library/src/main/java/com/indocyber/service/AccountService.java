package com.indocyber.service;

import com.indocyber.dto.RegisterDto;
import com.indocyber.dto.account.RegisterDTO;

public interface AccountService {
    // Register Account
    void registerAccount(RegisterDto dto);
}
