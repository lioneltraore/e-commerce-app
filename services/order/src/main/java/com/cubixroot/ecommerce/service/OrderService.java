package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.client.CustomerClient;
import com.cubixroot.ecommerce.client.ProductClient;
import com.cubixroot.ecommerce.dto.CustomerResponse;
import com.cubixroot.ecommerce.dto.OrderRequest;
import com.cubixroot.ecommerce.dto.ProductPurchaseResponse;
import com.cubixroot.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createProduct(OrderRequest orderRequest) {

        // check customer
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(()-> new BusinessException("Can not create product:: No customer exists with the provided ID"));

        // purchase the products --> product-ms
        List<ProductPurchaseResponse> productPurchaseResponse = productClient.purchaseProduct(orderRequest.products())
                .orElseThrow(()-> new BusinessException("An error occured while processing the products purchase"));

        // persit order

        // persit order line

        // start payment process

        // send order confirmation --> notification-ms

        return null;
    }
}
