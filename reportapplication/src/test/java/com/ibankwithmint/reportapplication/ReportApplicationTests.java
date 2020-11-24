package com.ibankwithmint.reportapplication;

import com.ibankwithmint.reportapplication.constants.OrderStatus;
import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.payload.OrderFilterResponseDTO;
import com.ibankwithmint.reportapplication.service.OrderService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
class ReportApplicationTests {



	@Autowired
	private OrderService orderService;


	@Test
	void contextLoads() {
	}

	@Before
	void setup(){
		SecureRandom secureRandom = new SecureRandom();
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
		}
	}
	@Test
	void responseNotNullTestAfterPopulatingData() throws Exception {

		OrderFilterResponseDTO orderFilterResponseDTO = orderService.filterAggregator(null,null);

		assertNotNull(orderFilterResponseDTO);

	}

	@Test
	void filterTestWithoutDateBoundry() throws Exception {

		OrderFilterResponseDTO orderFilterResponseDTO = orderService.filterAggregator(null,null);
		assertEquals(50, orderFilterResponseDTO.getCommutativeTotalOrders());

	}

	@Test
	void filterTestWithDateBoundry(){
		OrderFilterResponseDTO orderFilterResponseDTO = orderService.filterAggregator(LocalDateTime.now(),LocalDateTime.now().plusDays(5));

		assertNotNull(orderFilterResponseDTO);

	}

}
