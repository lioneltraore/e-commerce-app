package com.cubixroot.ecommerce.mapper;

import com.cubixroot.ecommerce.dto.ProductPurchaseResponse;
import com.cubixroot.ecommerce.dto.ProductRequest;
import com.cubixroot.ecommerce.dto.ProductResponse;
import com.cubixroot.ecommerce.model.Category;
import com.cubixroot.ecommerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        if(productRequest == null) {
            return null;
        }

        return Product.builder()
                .id(productRequest.id())
                .description(productRequest.description())
                .name(productRequest.name())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(
                        Category.builder()
                                .id(productRequest.categoryId())
                                .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(), quantity, product.getName(), product.getDescription(), product.getPrice()
        );
    }
}
