package com.ibankwithmint.reportapplication.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibankwithmint.reportapplication.model.OrderReport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderReportResponseDTO {

    private List<OrderReport> orders;
    private long totalOrders;
    private BigDecimal totalOrderAmount;
    private LocalDate date;


    public List<OrderReport> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderReport> orders) {
        this.orders = orders;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="E, MMM dd yyyy")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderReportResponseDTO{" +
                "orders=" + orders +
                ", totalOrders=" + totalOrders +
                ", totalOrderAmount=" + totalOrderAmount +
                ", date=" + date +
                '}';
    }
}
