package com.cubixroot.ecommerce.mapper;

import com.cubixroot.ecommerce.dto.OrderRequest;
import com.cubixroot.ecommerce.model.Order;
import org.springframework.stereotype.Service;


@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {

        if(orderRequest == null) {
            return null;
        }

        return Order.builder()
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }
}
