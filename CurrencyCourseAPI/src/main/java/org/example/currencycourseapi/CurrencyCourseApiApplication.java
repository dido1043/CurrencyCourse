package org.example.currencycourseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.currencycourseapi.client")
public class CurrencyCourseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCourseApiApplication.class, args);
    }

}
