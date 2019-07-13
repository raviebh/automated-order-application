package com.pep.int.web.rest;

import com.pep.int.AutomatedOrderSubmissionApp;
import com.pep.int.service.AutomatedOrderSubmissionKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = AutomatedOrderSubmissionApp.class)
public class AutomatedOrderSubmissionKafkaResourceIT {

    @Autowired
    private AutomatedOrderSubmissionKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        AutomatedOrderSubmissionKafkaResource kafkaResource = new AutomatedOrderSubmissionKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/automated-order-submission-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
