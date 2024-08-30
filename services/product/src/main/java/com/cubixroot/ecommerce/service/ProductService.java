package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.dto.ProductPurchaseRequest;
import com.cubixroot.ecommerce.dto.ProductPurchaseResponse;
import com.cubixroot.ecommerce.dto.ProductRequest;
import com.cubixroot.ecommerce.dto.ProductResponse;
import com.cubixroot.ecommerce.exception.ProductPurchaseException;
import com.cubixroot.ecommerce.mapper.ProductMapper;
import com.cubixroot.ecommerce.model.Product;
import com.cubixroot.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {

        Product product = productRepository.save(productMapper.toProduct(productRequest));

        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest) {

        // Extract IDs of products from the request
        List<Integer> productIds = productPurchaseRequest.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // Get product to purchase from DB
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<ProductPurchaseRequest> sortedRequest = productPurchaseRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest purchaseRequest = sortedRequest.get(i);

            if (product.getAvailableQuantity() < purchaseRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for stock with ID:: " + purchaseRequest.productId());
            }

            double newAvailableQuantity = product.getAvailableQuantity() - purchaseRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, purchaseRequest.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with ID:: " + productId));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
