package com.bankwithmint.inventorymanagement;

import com.bankwithmint.inventorymanagement.exception.ProductNotAvailableException;
import com.bankwithmint.inventorymanagement.model.Order;
import com.bankwithmint.inventorymanagement.model.Product;
import com.bankwithmint.inventorymanagement.payload.CreateOrderRequestDTO;
import com.bankwithmint.inventorymanagement.payload.CreateProductRequestDTO;
import com.bankwithmint.inventorymanagement.payload.CustomerDTO;
import com.bankwithmint.inventorymanagement.repository.ProductRepository;
import com.bankwithmint.inventorymanagement.service.OrderService;
import com.bankwithmint.inventorymanagement.service.ProductService;
import com.bankwithmint.inventorymanagement.utility.EncryptionUtil;
import com.bankwithmint.inventorymanagement.utility.OrderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class InventoryManagementApplicationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    private  Product product;
    String productName="Iphone 6", productDescription = "Brand new Iphone 6", productImageUrl="image.jpg";
    String customerName = " Test customer" , customerEmail = "test@gmail.com", customerAddress = "249 Ilupeju Road" , customerPhoneNumber = "2348148731425";

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setup(){
        product =productService.createProduct(new CreateProductRequestDTO(productName, productDescription, BigDecimal.valueOf(70,000), 10, 1, "phone.jpg"));
    }
    @Test()
    void productCreationTest(){
        assertNotNull(product);
    }
    @Test
    void availableProductsTest(){

        List<Product> products =productService.getAvailableProducts();
        assertFalse(products.isEmpty());
    }
    @Test
    void availableProductByIdTest(){
        Product p = productService.getAvailableProductById(product.getId());
        assertNotNull(p);
    }
    @Test
    void productUnavailableTest(){
        Product p = productService.getAvailableProductById(product.getId());
        p.setQuantityInStock(0);
        productService.updateProduct(p);
    assertThrows(ProductNotAvailableException.class, () -> productService.getAvailableProductById(p.getId()));
    }
    @Test
    void productImageUrlNullTest(){
        CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO(productName, productDescription, BigDecimal.valueOf(70000), 10, 1, null);
        assertThrows(DataIntegrityViolationException.class, () -> productService.createProduct(productRequestDTO));

    }

    @Test
    void productDescriptionNullTest(){
        CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO(productName, null, BigDecimal.valueOf(70000), 10, 1, productImageUrl);
        assertThrows(DataIntegrityViolationException.class, () -> productService.createProduct(productRequestDTO));

    }
    @Test
    void productNameNullTest(){
        CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO(null, "Brand new Iphone 6", BigDecimal.valueOf(70000), 10, 1, "image.jpg");
        assertThrows(DataIntegrityViolationException.class, () -> productService.createProduct(productRequestDTO));

    }
    @Test
    void productPriceNullTest(){
        CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO(productName, productDescription, null, 10, 1, productImageUrl);
        assertThrows(DataIntegrityViolationException.class, () -> productService.createProduct(productRequestDTO));

    }
    @Test
    void bigDecimalEncryptionAlgorithmTest(){
        assertNotEquals(EncryptionUtil.encryptBigDecimal(BigDecimal.TEN), BigDecimal.TEN);
    }
    @Test
    void bigDecimalDecryptionAlgorithmTest(){
        BigDecimal encryptedBigDecimal = EncryptionUtil.encryptBigDecimal(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, EncryptionUtil.decryptBigDecimal(encryptedBigDecimal));

    }

    @Test
    void createOrderWithAvailableProdctTest(){
        CreateOrderRequestDTO requestDTO = new CreateOrderRequestDTO(product.getId(), 1, new CustomerDTO(customerName,customerAddress, customerPhoneNumber, customerEmail));
        Order order = orderService.createOrder(requestDTO);
        assertNotNull(order);
    }
    @Test
    void productRequestQuantityExceedsQuantityInStockTest(){
        CreateOrderRequestDTO requestDTO = new CreateOrderRequestDTO(product.getId(), 11, new CustomerDTO(customerName,customerAddress, customerPhoneNumber, customerEmail));

        assertThrows(ProductNotAvailableException.class, () -> orderService.createOrder(requestDTO));

    }
    @Test
    void changeProductPriceWithoutAlteringCostOfAlreadyBookedItems(){
        BigDecimal currentPrice = product.getProductUnitPrice();
        CreateOrderRequestDTO requestDTO = new CreateOrderRequestDTO(product.getId(), 5, new CustomerDTO(customerName,customerAddress, customerPhoneNumber, customerEmail));
        Order order = orderService.createOrder(requestDTO);
        product.setProductUnitPrice(BigDecimal.valueOf(100,000));
        productService.updateProduct(product);
       order = orderService.findOrderById(order.getOrderId());

       assertEquals(currentPrice.multiply(BigDecimal.valueOf(order.getQuantity())), order.getCostOfItems());
    }
    @Test
    void concurrentOrderCreationTest(){
        Thread thread1 = new Thread() {
            public void run() {
                OrderUtil.validateOrderAgainstAvailableProductQuantity(product, 5);
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                OrderUtil.validateOrderAgainstAvailableProductQuantity(product, 5);
            }
        };
        thread1.run();
        thread2.run();
//        assertEquals(product.getQuantityInStock(), java.util.Optional.of(0));
    }

}
