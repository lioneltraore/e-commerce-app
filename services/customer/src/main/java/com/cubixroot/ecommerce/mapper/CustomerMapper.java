package com.cubixroot.ecommerce.mapper;

import com.cubixroot.ecommerce.dto.CustomerDTO;
import com.cubixroot.ecommerce.model.Customer;

public class CustomerMapper {

    public Customer toCustomer(CustomerDTO customerDTO) {

        if(customerDTO == null) {
            return null;
        }

        return Customer.builder()
                .id(customerDTO.id())
                .firstname(customerDTO.firstname())
                .lastname(customerDTO.lastname())
                .email(customerDTO.email())
                .address(customerDTO.address())
                .build();
    }
}
