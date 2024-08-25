package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.dto.CustomerDTO;
import com.cubixroot.ecommerce.mapper.CustomerMapper;
import com.cubixroot.ecommerce.model.Customer;
import com.cubixroot.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerDTO));
        return customer.getId();
    }
}
