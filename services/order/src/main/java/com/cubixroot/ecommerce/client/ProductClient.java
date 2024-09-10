package com.cubixroot.ecommerce.client;

import com.cubixroot.ecommerce.dto.ProductPurchaseResponse;
import com.cubixroot.ecommerce.dto.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<PurchaseRequest> purchaseRequests);
}
