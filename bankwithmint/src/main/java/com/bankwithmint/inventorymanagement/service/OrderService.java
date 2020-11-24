package com.bankwithmint.inventorymanagement.service;

import com.bankwithmint.inventorymanagement.model.Order;
import com.bankwithmint.inventorymanagement.payload.CreateOrderRequestDTO;

import java.util.UUID;

public interface OrderService {
    Order createOrder(CreateOrderRequestDTO requestDTO);
    Order findOrderById(UUID id);
}
