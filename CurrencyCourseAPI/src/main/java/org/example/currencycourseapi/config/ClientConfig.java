package org.example.currencycourseapi.config;


import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig{
    @Value("${freecurrencyapi.api-key}")
    private String apiKey;
    @Bean
    public RequestInterceptor currencyApiRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.query("apikey", apiKey);
        };
    }

}
