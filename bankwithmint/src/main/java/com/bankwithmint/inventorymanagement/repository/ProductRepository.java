package com.bankwithmint.inventorymanagement.repository;

import com.bankwithmint.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT p FROM Product p WHERE p.quantityInStock > 0 ORDER BY p.createdDate DESC")
    List<Product> findAllAvailableproducts();

}
