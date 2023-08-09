package com.sai.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import com.sai.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class kafkaProducerConfig {
    @Bean
    public ProducerFactory<String, User> producerFactory(){
        Map<String,Object> configProps = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092",
                                                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                                                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
                                                );
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "kafkaTemplate")
    public KafkaTemplate<String, User> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}