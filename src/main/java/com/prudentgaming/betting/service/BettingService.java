package com.prudentgaming.betting.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.prudentgaming.betting.model.BettingDataRequest;

@Service("bettingService")
/**
	* Application Service class to execute the Business Logic on certain data
*/
public class BettingService {
	
	public boolean addBets(BettingDataRequest bettingDataJson) throws JsonProcessingException {
		return true;
	}
}