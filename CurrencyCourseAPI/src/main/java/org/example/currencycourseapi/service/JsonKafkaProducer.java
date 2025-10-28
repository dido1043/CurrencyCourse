package org.example.currencycourseapi.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.currencycourseapi.model.CurrencyDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = Logger.getLogger(JsonKafkaProducer.class.getName());

    private final KafkaTemplate<String, CurrencyDto> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, CurrencyDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(CurrencyDto currencyDto){
        LOGGER.info("Sending message: " + currencyDto);
        Message<CurrencyDto> message = MessageBuilder
                .withPayload(currencyDto)
                .setHeader(KafkaHeaders.TOPIC, "currency_topic")
                .build();

        kafkaTemplate.send(message);
    }

}
