package com.bankwithmint.inventorymanagement.serviceimpl;

import com.bankwithmint.inventorymanagement.constants.OrderStatus;
import com.bankwithmint.inventorymanagement.model.Customer;
import com.bankwithmint.inventorymanagement.model.Order;
import com.bankwithmint.inventorymanagement.model.Product;
import com.bankwithmint.inventorymanagement.payload.CreateOrderRequestDTO;
import com.bankwithmint.inventorymanagement.repository.OrderRepository;
import com.bankwithmint.inventorymanagement.service.OrderService;
import com.bankwithmint.inventorymanagement.service.ProductService;
import com.bankwithmint.inventorymanagement.utility.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public Order createOrder(CreateOrderRequestDTO requestDTO) {
        Product product= productService.getAvailableProductById(requestDTO.getProductId());
        product = OrderUtil.validateOrderAgainstAvailableProductQuantity(product,requestDTO.getQuantity() );
        Order order = new Order();
        order.setCustomer(new Customer(requestDTO.getCustomer().getName(),requestDTO.getCustomer().getAddress(), requestDTO.getCustomer().getPhoneNumber(), requestDTO.getCustomer().getEmailAddress()));
        order.setPriceOnOrderCreation(product.getProductUnitPrice());
        order.setQuantity(requestDTO.getQuantity());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setProduct(product);
        order.setCostOfItems(order.getPriceOnOrderCreation().multiply(BigDecimal.valueOf(order.getQuantity())));
        LOGGER.info("------- Creating order {} ", order);
        Order savedOrder = orderRepository.saveAndFlush(order);
        producerService.sendMessage(OrderUtil.mapOrderToJsonObject(savedOrder).toString());

        return savedOrder;
    }

    @Override
    public Order findOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order found with specified id " + id));
    }


}
