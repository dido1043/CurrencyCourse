package org.example.currencycourseapi.service;

import org.example.currencycourseapi.client.FreeCurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final FreeCurrencyClient freeCurrencyClient;
    @Autowired
    public CurrencyService(FreeCurrencyClient freeCurrencyClient) {
        this.freeCurrencyClient = freeCurrencyClient;
    }

    public String getCurrency() {
        return freeCurrencyClient.getCurrecy();
    }


}
