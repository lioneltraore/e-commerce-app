package com.cubixroot.ecommerce.mapper;

import com.cubixroot.ecommerce.dto.OrderLineRequest;
import com.cubixroot.ecommerce.model.Order;
import com.cubixroot.ecommerce.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        if(orderLineRequest == null) {
            return null;
        }

        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .productId(orderLineRequest.productId())
                .order(Order.builder().id(orderLineRequest.orderId()).build())
                .build();
    }
}
