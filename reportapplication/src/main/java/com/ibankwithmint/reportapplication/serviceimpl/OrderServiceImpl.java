package com.ibankwithmint.reportapplication.serviceimpl;

import com.ibankwithmint.reportapplication.model.OrderReport;
import com.ibankwithmint.reportapplication.payload.OrderFilterResponseDTO;
import com.ibankwithmint.reportapplication.payload.OrderReportResponseDTO;
import com.ibankwithmint.reportapplication.repository.OrderRepository;
import com.ibankwithmint.reportapplication.service.OrderService;
import com.ibankwithmint.reportapplication.utility.ReportUtil;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);


    @Override
    public OrderReport findOrderById(UUID id) {

        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.FOUND, "No order found with the specified I.D  -> " + id));
    }

    @Override
    public OrderReport createReport(OrderReport report) {
        OrderReport savedReport = orderRepository.save(report);

        LOGGER.info("Saving report {}", savedReport);

        return savedReport;
    }

    @Override
    public OrderFilterResponseDTO getAllOrderReport() {

        List<OrderReportResponseDTO> orderResponse = new ArrayList<>();
        List<OrderReport> report = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderCreationDate"));
        List<LocalDate> allDates = report.parallelStream().map( e -> e.getOrderCreationDate().toLocalDate()).distinct().collect(Collectors.toList());
        for(LocalDate dt : allDates){

            List<OrderReport> r =  report.parallelStream().filter((or) -> or.getOrderCreationDate().toLocalDate().equals(dt)).collect(Collectors.toList());
            LOGGER.info("Date {} reports {}", dt, r);
            orderResponse.add(ReportUtil.mapReportToResponseDTO(r,dt));
        }

        return new OrderFilterResponseDTO(orderResponse, report.size(),ReportUtil.calculateCummulativeOrderAmount(report));
    }

    @Override
    public OrderFilterResponseDTO getOrdersBetween(final LocalDateTime from, LocalDateTime to) {
        List<OrderReportResponseDTO> orderResponse = new ArrayList<>();
        List<OrderReport> report = orderRepository.findByOrderCreationDateBetween(from,to,Sort.by(Sort.Direction.DESC, "orderCreationDate"));
        List<LocalDate> allDates = report.parallelStream().map( e -> e.getOrderCreationDate().toLocalDate()).distinct().collect(Collectors.toList());
        for(LocalDate dt : allDates){

            List<OrderReport> r =  report.parallelStream().filter((or) -> or.getOrderCreationDate().toLocalDate().equals(dt)).collect(Collectors.toList());
            orderResponse.add(ReportUtil.mapReportToResponseDTO(r,dt));
        }

        return new OrderFilterResponseDTO(orderResponse, report.size(),ReportUtil.calculateCummulativeOrderAmount(report));
    }

    @Override
    public OrderFilterResponseDTO filterAggregator(LocalDateTime from, LocalDateTime to) {

        if(from == null || to == null){
            return getAllOrderReport();
        }else{
            return getOrdersBetween(from, to);
        }
    }


}
