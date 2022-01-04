package com.cpa.kafka.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class KafkaProducer {
		
		
	    private static final String TOPIC = "data";

	    @Autowired
	    private KafkaTemplate kafkaTemplate;

	    
	    public void sendMessage(String modifyFinalData ) throws JsonProcessingException {
	        System.out.println(String.format("Producer produces data --->>>>>>>>>>>>>> ", modifyFinalData));
	        this.kafkaTemplate.send(TOPIC,  modifyFinalData);
	    }
		
		
	}


