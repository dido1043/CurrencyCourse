package org.example.currencycourseapi.client;

import feign.Response;
import org.example.currencycourseapi.config.ClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CurrencyClient",
        url = "https://api.freecurrencyapi.com/v1/latest",
        configuration = ClientConfig.class)
public interface FreeCurrencyClient {

    @GetMapping("/")
    public String getCurrecy();
}
