package com.cubixroot.ecommerce.dto;

import com.cubixroot.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
         String id,

         @NotNull(message = "Customer fistname is required")
         String firstname,

         @NotNull(message = "Customer lastname is required")
         String lastname,

         @NotNull(message = "Customer email is required")
         @Email(message = "Customer email is not valid email address")
         String email,

         Address address
) {
}
