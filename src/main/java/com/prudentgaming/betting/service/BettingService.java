package com.prudentgaming.betting.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.prudentgaming.betting.model.BettingDataRequest;
import com.prudentgaming.betting.repository.BettingDataRepository;
import com.prudentgaming.betting.model.Bet;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;

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
		try{
			List<Bet> list = Arrays.asList(bettingDataJson.getBets());
			bettingRepo.saveAll(list);
		}catch(Exception ex){
			//TODO
			// Add code for handling or retrying the saving process in case of failure
			return false;
		}
		return true;
	}
}