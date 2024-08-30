package com.cubixroot.ecommerce.controller;

import com.cubixroot.ecommerce.dto.ProductPurchaseRequest;
import com.cubixroot.ecommerce.dto.ProductPurchaseResponse;
import com.cubixroot.ecommerce.dto.ProductRequest;
import com.cubixroot.ecommerce.dto.ProductResponse;
import com.cubixroot.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> productPurchaseRequests) {
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequests));
    }

    @GetMapping("/{product-id}")
    ResponseEntity<ProductResponse> findProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping
    ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
