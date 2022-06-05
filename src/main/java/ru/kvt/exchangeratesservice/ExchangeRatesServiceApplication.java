package ru.kvt.exchangeratesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExchangeRatesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesServiceApplication.class, args);
    }

}
