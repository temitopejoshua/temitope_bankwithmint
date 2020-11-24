package com.ibankwithmint.reportapplication.controller;

import com.ibankwithmint.reportapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/order-report")
public class OrderReportController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<Object> filterReport(@RequestParam(value = "fromDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate, @RequestParam(value = "toDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate){
        return new ResponseEntity<>(orderService.filterAggregator(fromDate,toDate), HttpStatus.OK);
    }
}
