package com.ibankwithmint.reportapplication.utility;

import com.ibankwithmint.reportapplication.constants.OrderStatus;
import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private static Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    @Autowired
    private OrderService orderService;

   private final  SecureRandom secureRandom = new SecureRandom();
    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<50; i++){
            OrderReport orderReport = new OrderReport();
            orderReport.setOrderId(UUID.randomUUID());
            orderReport.setOrderStatus(OrderStatus.PROCESSING);
            orderReport.setOrderCreationDate(LocalDateTime.now().plusDays(secureRandom.nextInt(5)));
            orderReport.setUnitPrice(new BigDecimal(100).multiply(BigDecimal.valueOf(secureRandom.nextInt(10))));
            orderReport.setQuantity(secureRandom.nextInt(20));
            orderReport.setProductName("Test Product");
            orderReport.setCustomerPhoneNumber("+23455" + secureRandom.nextInt(676));
            orderReport.setCustomerAddress("Address test");
            orderReport.setCustomerName("Test Customer");
            orderReport.setTotalCostOfItems(BigDecimal.TEN);
            orderService.createReport(orderReport);
            LOGGER.info("Successfully saved {}" , orderReport);
        }

    }
}
