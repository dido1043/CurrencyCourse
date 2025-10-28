package org.example.currencycourseapi.controller;


import org.example.currencycourseapi.model.CurrencyDto;
import org.example.currencycourseapi.service.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class JsonMessageController {

    private final JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody CurrencyDto currencyDto){
        jsonKafkaProducer.sendMessage(currencyDto);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }
}
