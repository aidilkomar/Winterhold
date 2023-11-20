package com.indocyber.dto;

import com.indocyber.validation.Compare;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Compare(message="Password is not matched.", firstField="password", secondField="passwordConfirmation")
public class RegisterDto {

    @NotBlank(message="Username is required")
    @Size(max=20, message="Username can't be more than 20 characters.")
    private String username;

    @NotBlank(message="Password is required")
    @Size(max=20, message="Password can't be more than 20 characters.")
    private String password;

    @NotBlank(message="Password confirmation is required")
    @Size(max=20, message="Password confirmation can't be more than 20 characters.")
    private String passwordConfirmation;

    public RegisterDto() {
    }

    public RegisterDto(String username, String password, String passwordConfirmation) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
