package com.bankwithmint.inventorymanagement.controller;

import com.bankwithmint.inventorymanagement.payload.CreateOrderRequestDTO;
import com.bankwithmint.inventorymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Object> orderProduct(@Validated @RequestBody CreateOrderRequestDTO requestDTO){

        return new ResponseEntity<>(orderService.createOrder(requestDTO), HttpStatus.CREATED);
    }
}
