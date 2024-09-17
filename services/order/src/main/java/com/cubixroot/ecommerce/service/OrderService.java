package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.client.CustomerClient;
import com.cubixroot.ecommerce.client.ProductClient;
import com.cubixroot.ecommerce.dto.*;
import com.cubixroot.ecommerce.exception.BusinessException;
import com.cubixroot.ecommerce.mapper.OrderMapper;
import com.cubixroot.ecommerce.model.Order;
import com.cubixroot.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest orderRequest) {

        // check customer
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(()-> new BusinessException("Can not create order:: No customer exists with the provided ID"));

        // purchase the products --> product-ms
        productClient.purchaseProduct(orderRequest.products())
                .orElseThrow(()-> new BusinessException("Can not create order:: An error occured while processing the products purchase"));

        // persit order
        Order order = orderRepository.save(orderMapper.toOrder(orderRequest));

        // persit order line
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );
        }

        // start payment process

        // send order confirmation --> notification-ms (Kafka)

        return null;
    }
}
