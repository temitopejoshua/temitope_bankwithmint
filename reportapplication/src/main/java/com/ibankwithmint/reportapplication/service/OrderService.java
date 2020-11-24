package com.ibankwithmint.reportapplication.service;

import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.payload.OrderFilterResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderService {
    OrderReport findOrderById(UUID id);
    OrderReport createReport(OrderReport report);
    OrderFilterResponseDTO getAllOrderReport();
    OrderFilterResponseDTO getOrdersBetween(LocalDateTime from, LocalDateTime to);
    OrderFilterResponseDTO filterAggregator(LocalDateTime from, LocalDateTime to);

}
