package org.example.currencycourseapi.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.example.currencycourseapi.model.CurrencyDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = Logger.getLogger(JsonKafkaProducer.class.getName());

    private final KafkaTemplate<String, CurrencyDto> kafkaTemplate;
    private final NewTopic currencyTopic;
    public JsonKafkaProducer(KafkaTemplate<String, CurrencyDto> kafkaTemplate,
                             @Qualifier("currencyTopic") NewTopic currencyTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.currencyTopic = currencyTopic;
    }
    public void sendMessage(CurrencyDto currencyDto){
        LOGGER.info("Sending message: " + currencyDto);

        Message<CurrencyDto> message = MessageBuilder

                .withPayload(currencyDto)
                .setHeader(KafkaHeaders.TOPIC, currencyTopic.name())
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.TIMESTAMP, Instant.now().toEpochMilli())
                .build();

        kafkaTemplate.send(message);
    }

}
