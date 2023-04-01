package com.prudentgaming.betting.model;

import lombok.Data;


@Data 
/** 
	* This class represent the structure of the Json Request for the POST request to save the data.
	* If the Json Structure is not as per this class, then it will result into exception
	*
*/
public class BettingDataRequest {
	private Bet[] bets;
}