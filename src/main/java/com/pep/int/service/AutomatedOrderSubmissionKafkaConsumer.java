package com.pep.int.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AutomatedOrderSubmissionKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(AutomatedOrderSubmissionKafkaConsumer.class);
    private static final String TOPIC = "topic_automatedordersubmission";

    @KafkaListener(topics = "topic_automatedordersubmission", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
