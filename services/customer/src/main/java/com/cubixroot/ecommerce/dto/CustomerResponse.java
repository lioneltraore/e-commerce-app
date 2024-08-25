package com.cubixroot.ecommerce.dto;

import com.cubixroot.ecommerce.model.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address) {
}
