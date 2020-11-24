package com.ibankwithmint.reportapplication.repository;

import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.model.OrderReport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository  extends JpaRepository<OrderReport, UUID> {
    List<OrderReport> findByOrderCreationDateBetween(LocalDateTime from, LocalDateTime to, Sort sort);
}
