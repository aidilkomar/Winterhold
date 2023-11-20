package com.indocyber.rest;

import com.indocyber.dto.account.RequestTokenDTO;
import com.indocyber.dto.account.ResponseTokenDTO;
import com.indocyber.service.AccountService;
import com.indocyber.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private AccountService accountService;

    private UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private JwtToken jwtToken;

    @Autowired
    public AccountRestController(AccountService accountService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtToken jwtToken) {
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
    }

    @PostMapping(value = "/authenticate")
    public ResponseTokenDTO post(@RequestBody RequestTokenDTO dto) {
        try {
            /**
             * When the user submits their username and password,
             * the UsernamePasswordAuthenticationFilter creates a UsernamePasswordAuthenticationToken,
             * which is a type of Authentication,
             * by extracting the username and password from the HttpServletRequest instance.
             */
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            System.out.println(token);
            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println("Is authenticate? " + authentication.isAuthenticated());
            System.out.println("Principal: " + authentication.getPrincipal());
            System.out.println("Credential: " + authentication.getCredentials());
        } catch (Exception e) {
            System.out.println("Can not authenticate!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cant authenticate!", e);
        }

        String token = jwtToken.generateToken(dto.getSubject(), dto.getUsername(), dto.getSecretKey(), dto.getAudience());

        ResponseTokenDTO response = new ResponseTokenDTO();
        response.setUsername(dto.getUsername());
        response.setToken(token);

        return response;
    }
}