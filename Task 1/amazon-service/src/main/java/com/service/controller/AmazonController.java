
package com.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class AmazonController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.amazon}")
    private String amazonTopic;

    @PostMapping("/create")
    public String createOrder(@RequestBody String orderDetails) {
        kafkaTemplate.send(amazonTopic, orderDetails);
        return "✅ Order placed successfully and sent to Flipkart!";
    }
}
