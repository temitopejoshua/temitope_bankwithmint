package com.bankwithmint.inventorymanagement.payload;


import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateOrderRequestDTO {

    @NotNull
    private UUID productId;
    @NotNull
    private long quantity;
    private CustomerDTO customer;

    public CreateOrderRequestDTO(@NotNull UUID productId, @NotNull long quantity, CustomerDTO customer) {
        this.productId = productId;
        this.quantity = quantity;
        this.customer = customer;
    }

    public CreateOrderRequestDTO() {
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
