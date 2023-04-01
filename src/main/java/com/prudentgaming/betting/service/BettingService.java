package com.prudentgaming.betting.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
    public BettingService() {
    }
	
	public boolean addBets(BettingDataRequest bettingDataJson) throws JsonProcessingException {
		for(Bet bet: bettingDataJson.getBets()){
			bettingRepo.save(bet);
		}
		return true;
	}
}