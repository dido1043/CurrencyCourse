package org.example.currencycourseapi.service;

import org.example.currencycourseapi.client.FreeCurrencyClient;
import org.example.currencycourseapi.exception.FeignErrorDecoder;
import org.example.currencycourseapi.exception.InternalServerException;
import org.example.currencycourseapi.model.Currency;
import org.example.currencycourseapi.model.CurrencyDto;
import org.example.currencycourseapi.repository.CurrencyRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {
    private final FreeCurrencyClient freeCurrencyClient;
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CurrencyService(FreeCurrencyClient freeCurrencyClient,
                           CurrencyRepository currencyRepository) {
        this.freeCurrencyClient = freeCurrencyClient;
        this.currencyRepository = currencyRepository;

        this.modelMapper = new ModelMapper();
    }

    public List<CurrencyDto> getCurrency() {
        List<CurrencyDto> currencyList = currencyRepository.findAll()
                .stream()
                .filter(c -> c.getLast_price_update().toLocalDate().equals(LocalDateTime.now().toLocalDate()))
                .map(this :: mapToDto)
                .toList();

        if(currencyList.isEmpty()) {
            saveNecessaryData(freeCurrencyClient.getCurrecy());
        }

        return currencyList;
    }

    public void saveNecessaryData(String data){

        try{
            JSONObject rootObj = new JSONObject(data);
            JSONObject jsonObject = rootObj.getJSONObject("data");

            for(String key : jsonObject.keySet()){
                if (key.equals("EUR") || key.equals("USD") || key.equals("CNY")){
                    Currency currency = new Currency(key, jsonObject.getDouble(key));
                    currency.setLast_price_update(LocalDateTime.now());
                    currencyRepository.save(currency);
                }
            }
        }catch(InternalServerException e){
            throw e;
        }
    }

    private CurrencyDto mapToDto(Currency currency){
        return modelMapper.map(currency, CurrencyDto.class);
    }
}
