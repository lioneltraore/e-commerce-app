package com.cubixroot.ecommerce.dto;

import com.cubixroot.ecommerce.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method must be precise")
        PaymentMethod paymentMethod,

        @NotBlank(message = "Customer must be present")
        String customerId,

        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}
