package org.example.currencycourseapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Currency", url = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_DmQd6rJvNAhNzRbaLfa3BNrLBq4PlRGzKKkNfjNk")
public interface FreeCurrencyClient {
    @GetMapping("/")
    public String getCurrecy();
}
