package com.ibankwithmint.reportapplication.payload;

import java.math.BigDecimal;
import java.util.List;

public class OrderFilterResponseDTO {

    List<OrderReportResponseDTO> report;
    long commutativeTotalOrders;
    BigDecimal cumulativeTotalOrderAmount;

    public OrderFilterResponseDTO() {
    }

    public OrderFilterResponseDTO(List<OrderReportResponseDTO> report, long commutativeTotalOrders, BigDecimal cumulativeTotalOrderAmount) {
        this.report = report;
        this.commutativeTotalOrders = commutativeTotalOrders;
        this.cumulativeTotalOrderAmount = cumulativeTotalOrderAmount;
    }

    public List<OrderReportResponseDTO> getReport() {
        return report;
    }

    public void setReport(List<OrderReportResponseDTO> report) {
        this.report = report;
    }

    public long getCommutativeTotalOrders() {
        return commutativeTotalOrders;
    }

    public void setCommutativeTotalOrders(long commutativeTotalOrders) {
        this.commutativeTotalOrders = commutativeTotalOrders;
    }

    public BigDecimal getCumulativeTotalOrderAmount() {
        return cumulativeTotalOrderAmount;
    }

    public void setCumulativeTotalOrderAmount(BigDecimal cumulativeTotalOrderAmount) {
        this.cumulativeTotalOrderAmount = cumulativeTotalOrderAmount;
    }
}
