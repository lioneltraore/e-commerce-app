package com.cubixroot.ecommerce.repository;

import com.cubixroot.ecommerce.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
