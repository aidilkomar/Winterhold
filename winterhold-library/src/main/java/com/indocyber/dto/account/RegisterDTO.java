package com.indocyber.dto.account;

public class RegisterDTO {

    private String username;

    private String password;

    private String passwordConfirmation;

    public RegisterDTO(){}

    public RegisterDTO(String username, String password, String passowrdConfirmation) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passowrdConfirmation;
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
