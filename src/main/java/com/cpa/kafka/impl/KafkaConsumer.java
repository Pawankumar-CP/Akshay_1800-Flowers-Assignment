package com.cpa.kafka.impl;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	
	
    @KafkaListener(topics = "data", groupId = "group_id")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Kafka Consumer -> Consumed message -> %s", message));
    }
	
	
}