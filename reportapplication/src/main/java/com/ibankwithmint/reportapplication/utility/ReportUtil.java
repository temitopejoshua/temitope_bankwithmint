package com.ibankwithmint.reportapplication.utility;

import com.ibankwithmint.reportapplication.constants.OrderStatus;
import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.payload.OrderReportResponseDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReportUtil {
    public static OrderReport mapJsonObjectToReport(String message){
        OrderReport report = new OrderReport();
        try {
            JSONObject jsonObject = new JSONObject(message);
            report.setOrderId(UUID.fromString(jsonObject.getString("orderId")));
            report.setCustomerName(jsonObject.getString("customerName"));
            report.setCustomerAddress(jsonObject.getString("customerAddress"));
            report.setCustomerPhoneNumber(jsonObject.getString("customerPhoneNumber"));
            report.setUnitPrice(BigDecimal.valueOf(jsonObject.getDouble("productUnitCost")));
            report.setTotalCostOfItems(new BigDecimal(jsonObject.getString("totalCostOfItems")));
            report.setProductName(jsonObject.getString("productName"));
            report.setOrderCreationDate(LocalDateTime.parse(jsonObject.getString("orderCreatedDate")));
            report.setQuantity(jsonObject.getLong("quantity"));
            report.setOrderStatus(OrderStatus.SUCCESSFUL);
        }catch (JSONException ex){
            ex.printStackTrace();
        }

        return report;
    }

    public static OrderReportResponseDTO mapReportToResponseDTO(List<OrderReport> report, LocalDate date){
        OrderReportResponseDTO responseDTO = new OrderReportResponseDTO();
        responseDTO.setDate(date);
        responseDTO.setOrders(report);
        responseDTO.setTotalOrders(report.size());
        BigDecimal totalOrderAmount =  report.parallelStream().map(x -> x.getTotalCostOfItems())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        responseDTO.setTotalOrderAmount(totalOrderAmount);
    return responseDTO;
    }

    public static BigDecimal calculateCummulativeOrderAmount(List<OrderReport> report ){

        return report.parallelStream().map(x -> x.getTotalCostOfItems())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
