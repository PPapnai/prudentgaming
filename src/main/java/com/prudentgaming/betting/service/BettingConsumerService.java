package com.prudentgaming.betting.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.prudentgaming.betting.model.Bet;

@Slf4j
@Service("bettingConsumerService")
public class BettingConsumerService {
	
	@Value("${bet.threshold}")
	private double threshold;
	
	// Listen for the bets droped in t.bet.detail topic to send notification as they are above threshold
	@KafkaListener(topics = "t.bet.detail", containerFactory = "kafkaListenerContainerFactory")
    public void betsAboveThreasholdListener(Bet betDetail) {
        log.info("Sending notification for bet above threshold("+threshold+"): " + betDetail.toString());
    }

}
