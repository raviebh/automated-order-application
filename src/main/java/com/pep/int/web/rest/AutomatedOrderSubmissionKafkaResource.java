package com.pep.int.web.rest;

import com.pep.int.service.AutomatedOrderSubmissionKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/automated-order-submission-kafka")
public class AutomatedOrderSubmissionKafkaResource {

    private final Logger log = LoggerFactory.getLogger(AutomatedOrderSubmissionKafkaResource.class);

    private AutomatedOrderSubmissionKafkaProducer kafkaProducer;

    public AutomatedOrderSubmissionKafkaResource(AutomatedOrderSubmissionKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
