package com.prudentgaming.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import com.prudentgaming.betting.model.Bet;

@Slf4j
@Service("bettingProducerService")
public class BettingProducerService {
	
	@Value("${kafka.topic}")
    private String topic;

    private KafkaTemplate<String, Serializable> kafkaTemplate;

    @Autowired
    public BettingProducerService(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Bet message) {
        ListenableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Serializable>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message = {} dut to: {}", message.toString(), ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Serializable> result) {
                log.info("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
            }
        });
    }

}
