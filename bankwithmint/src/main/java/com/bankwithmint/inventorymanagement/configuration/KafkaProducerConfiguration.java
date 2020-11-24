package com.bankwithmint.inventorymanagement.configuration;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public NewTopic topic1() {
        return new NewTopic("orders", 1, (short) 1);
    }
}
