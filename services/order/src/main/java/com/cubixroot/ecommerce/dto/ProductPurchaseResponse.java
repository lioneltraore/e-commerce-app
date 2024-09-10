package com.cubixroot.ecommerce.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        double quantity,
        String name,
        String description,
        BigDecimal price
) {
}
