
package com.cons.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cons")
public class FlipkartApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlipkartApplication.class, args);
    }
}
