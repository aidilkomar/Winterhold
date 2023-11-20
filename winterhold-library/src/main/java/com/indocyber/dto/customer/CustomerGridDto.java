package com.indocyber.dto.customer;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class CustomerGridDto {

    private String membershipNumber;

    private String fullName;

    private LocalDate birthDate;

    private String gender;

    private String phone;

    private String address;

    private LocalDate membershipExpireDate;

    public CustomerGridDto() {
    }

    public CustomerGridDto(String membershipNumber, String fullName, LocalDate birthDate, String gender, String phone, String address, LocalDate membershipExpireDate) {
        this.membershipNumber = membershipNumber;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.membershipExpireDate = membershipExpireDate;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getMembershipExpireDate() {
        return membershipExpireDate;
    }

    public void setMembershipExpireDate(LocalDate membershipExpireDate) {
        this.membershipExpireDate = membershipExpireDate;
    }
}
