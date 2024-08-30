package com.cubixroot.ecommerce.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double vailableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
