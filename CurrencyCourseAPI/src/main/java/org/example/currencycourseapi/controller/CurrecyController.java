package org.example.currencycourseapi.controller;

import org.example.currencycourseapi.model.CurrencyDto;
import org.example.currencycourseapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrecyController {

    private final CurrencyService currencyService;
    @Autowired
    public CurrecyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping("/get")
    public ResponseEntity<List<CurrencyDto>> getCurrency(){
        return ResponseEntity.ok(currencyService.getCurrency());
    }
}
