
package com.cons.controller;


import org.springframework.web.bind.annotation.*;

import com.cons.Filpcart;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class FlipkartConsumerController {

    private final Filpcart filpcart;

    public FlipkartConsumerController(Filpcart filpcart) {
        this.filpcart = filpcart;
    }

    @GetMapping("/received")
    public List<String> getReceivedOrders() {
        List<String> orders = filpcart.getMessages();
        System.out.println("📦 Returning orders: " + orders);
        return orders;
    }

    
    
}
