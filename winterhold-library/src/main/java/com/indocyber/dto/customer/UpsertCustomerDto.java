package com.indocyber.dto.customer;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UpsertCustomerDto {

    @NotNull(message="Membership Number is required")
    @Size(max=20, message="title can't be more than 50 characters.")
    private String membershipNumber;

    @NotNull(message="First Name is required")
    @Size(max=50, message="title can't be more than 50 characters.")
    private String firstName;

    private String lastName;

    @NotNull(message="Birth Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String gender;

    private String phone;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membershipExpireDate;

    public UpsertCustomerDto() {
    }

    public UpsertCustomerDto(String membershipNumber, String firstName, String lastName, LocalDate birthDate, String gender, String phone, String address, LocalDate membershipExpireDate) {
        this.membershipNumber = membershipNumber;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
