package com.prudentgaming.betting.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.prudentgaming.betting.model.BettingDataRequest;
import com.prudentgaming.betting.repository.BettingDataRepository;
import com.prudentgaming.betting.model.Bet;

@Service("bettingService")
/**
	* Application Service class to execute the Business Logic on certain data
*/
public class BettingService {
	
	@Autowired
	private BettingDataRepository bettingRepo;
	
	@Value("${bet.threshold}")
	private double threshold;
	
	private BettingProducerService bettingProducer;

    @Autowired
    public BettingService(BettingProducerService bettingProducer) {
        this.bettingProducer = bettingProducer;
    }
	
	public boolean addBets(BettingDataRequest bettingDataJson) throws JsonProcessingException {
		for(Bet bet: bettingDataJson.getBets()){
			bettingRepo.save(bet);
			if(bet.getTotalReturns() >= threshold){
				bettingProducer.sendMessage(bet);
			}
		}
		return true;
	}
}