package com.indocyber.service;

import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.dto.customer.UpsertCustomerDto;
import com.indocyber.entity.Customer;
import com.indocyber.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private final int rowsInPage = 10;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerGridDto> getCustomerGrid(Integer page) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<CustomerGridDto> grid = customerRepository.findAllCustomer(pagination);
        return grid;
    }

    @Override
    public long getTotalPages() {
        double totalData = (double) (customerRepository.count());
        long totalPage = (long)(Math.ceil(totalData/rowsInPage));
        return totalPage;
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public UpsertCustomerDto getUpdateCustomer(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer customer = customerOptional.get();
        UpsertCustomerDto customerDto = new UpsertCustomerDto(
                customer.getMembershipNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getMembershipExpireDate()
        );
        return customerDto;
    }

    @Override
    public void saveCustomer(UpsertCustomerDto dto) {
        LocalDate expireDate = LocalDate.now().plusYears(2);
        Optional<Customer> repository = customerRepository.findById(dto.getMembershipNumber());
        Customer customer;
        if(repository.isPresent()){
            customer = repository.get();
            customer.setFirstName(dto.getFirstName());
            customer.setLastName(dto.getLastName());
            customer.setBirthDate(dto.getBirthDate());
            customer.setGender(dto.getGender());
            customer.setPhone(dto.getPhone());
            customer.setAddress(dto.getAddress());
            customer.setMembershipExpireDate(expireDate);
        } else {
            customer = new Customer(
                    dto.getMembershipNumber(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getBirthDate(),
                    dto.getGender(),
                    dto.getPhone(),
                    dto.getAddress(),
                    expireDate
            );
        }
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void saveCustomerExtend(String id) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = byId.get();

        LocalDate extend = customer.getMembershipExpireDate().plusYears(2);
        customer.setMembershipExpireDate(extend);
        customerRepository.save(customer);
    }

}
