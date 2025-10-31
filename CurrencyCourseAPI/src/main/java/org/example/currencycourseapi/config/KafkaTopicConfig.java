package org.example.currencycourseapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean("currencyTopic")
    public NewTopic currencyTopic(){
        return TopicBuilder.name("currency_topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
