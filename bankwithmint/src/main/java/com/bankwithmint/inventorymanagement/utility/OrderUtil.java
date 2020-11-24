package com.bankwithmint.inventorymanagement.utility;

import com.bankwithmint.inventorymanagement.exception.ProductNotAvailableException;
import com.bankwithmint.inventorymanagement.model.Order;
import com.bankwithmint.inventorymanagement.model.Product;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Singleton;

@Singleton
public class OrderUtil {

    public static synchronized Product validateOrderAgainstAvailableProductQuantity(Product product, long quantity){
        if(product.getQuantityInStock() < quantity)
            throw new ProductNotAvailableException(HttpStatus.BAD_REQUEST, "Order request quantity exceeds quantity in stock  Quantity in stock = " + product.getQuantityInStock());

        if(quantity < product.getMinimumOrderQuantity() )
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order request quantity is below minimum order quantity which is " + product.getMinimumOrderQuantity());

        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        return product;
    }

    public static JSONObject mapOrderToJsonObject(Order order) {
        JSONObject response = new JSONObject();
        try {
            response.put("orderId", order.getOrderId());
            response.put("customerName", order.getCustomer().getName());
            response.put("customerAddress", order.getCustomer().getAddress());
            response.put("customerPhoneNumber", order.getCustomer().getPhoneNumber());
            response.put("totalCostOfItems", order.getCostOfItems());
            response.put("productName", order.getProduct().getProductName());
            response.put("orderCreatedDate", order.getCreatedDate());
            response.put("productUnitCost", order.getProduct().getProductUnitPrice());
            response.put("quantity", order.getQuantity());
            response.put("productCurrency", order.getProduct().getProductCurrency());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return response;

    }
}
