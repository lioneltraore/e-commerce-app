package com.cubixroot.ecommerce.service;

import com.cubixroot.ecommerce.dto.OrderLineRequest;
import com.cubixroot.ecommerce.mapper.OrderLineMapper;
import com.cubixroot.ecommerce.model.OrderLine;
import com.cubixroot.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = orderLineMapper.toOrderLine(orderLineRequest);

        return orderLineRepository.save(orderLine).getId();
    }
}
