
package com.cons;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

@Service
public class Filpcart {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "amazon-orders", groupId = "flipkart-group")
    public void listen(String message) {
        messages.add(message);
        System.out.println("📩 Received from Amazon: " + message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
