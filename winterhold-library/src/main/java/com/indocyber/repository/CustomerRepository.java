package com.indocyber.repository;

import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("""
            SELECT new com.indocyber.dto.customer.CustomerGridDto(cus.membershipNumber, CONCAT(cus.firstName, ' ', cus.lastName), cus.birthDate, cus.gender, cus.phone, cus.address, cus.membershipExpireDate)
            FROM Customer AS cus""")
    List<CustomerGridDto> findAllCustomer(Pageable pagination);

    @Query("""
			SELECT COUNT(*)
			FROM Customer AS cus
			""")
    public long count();
}
