package com.indocyber.service;

import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.dto.customer.UpsertCustomerDto;
import com.indocyber.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerGridDto> getCustomerGrid(Integer page);

    long getTotalPages();

    List<Customer> findAllCustomer();

    UpsertCustomerDto getUpdateCustomer(String id);

    void saveCustomer(UpsertCustomerDto dto);

    void deleteCustomer(String id);

    void saveCustomerExtend(String id);
}
