package com.ibankwithmint.reportapplication.service;

import com.ibankwithmint.reportapplication.utility.ReportUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class ConsumerService {
    private static final String TOPIC = "orders";
    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    OrderService orderService;

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String message) throws IOException {
        LOGGER.info(String.format("#### -> Received message -> %n %s", message));
        if(message !=null)
            orderService.createReport(ReportUtil.mapJsonObjectToReport(message));
    }
}
