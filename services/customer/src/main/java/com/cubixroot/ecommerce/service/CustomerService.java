package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.dto.CustomerRequest;
import com.cubixroot.ecommerce.dto.CustomerResponse;
import com.cubixroot.ecommerce.exception.CustomerNotFoundException;
import com.cubixroot.ecommerce.mapper.CustomerMapper;
import com.cubixroot.ecommerce.model.Customer;
import com.cubixroot.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID:: %s", customerRequest.id())
                ));

        Customer mergedCustomer = mergerCustomer(customer, customerRequest);

        customerRepository.save(mergedCustomer);
    }

    private Customer mergerCustomer(Customer customer, CustomerRequest customerRequest) {

        return Customer.builder()
                .id(customerRequest.id())
                .firstname(StringUtils.isNotBlank(customerRequest.firstname()) ?
                        customerRequest.firstname() : customer.getFirstname() )
                .lastname(StringUtils.isNotBlank(customerRequest.lastname()) ?
                        customerRequest.lastname() : customer.getLastname() )
                .email(StringUtils.isNotBlank(customerRequest.email()) ?
                        customerRequest.email() : customer.getEmail() )
                .address(customerRequest.address() != null ?
                        customerRequest.address() : customer.getAddress() )
                .build();
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findCustomerById(String customerId) {

        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("No customer found with the provided ID:: %s", customerId)
                ));
    }

    public void deleteUser(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
