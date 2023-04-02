package com.prudentgaming.betting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Arrays;
import com.prudentgaming.betting.repository.BettingDataRepository;
import com.prudentgaming.betting.model.SummaryRequest;

@Controller
public class PrudentGamingController {
	
	@Autowired
	private BettingDataRepository bettingRepo;
		
	@GetMapping("/{gameName}/{clientId}/bet")
	public String getBetsByGameAndClientId(final @PathVariable("clientId") Integer clientId, final @PathVariable("gameName") String gameName, Model model) throws JsonProcessingException{
		model.addAttribute("bets", bettingRepo.findBetsByGameAndClientId(gameName, clientId));
		return "betting_summary";
	}
		
	@GetMapping("/game/{clientId}/bet")
	public String getBetsByClientId(final @PathVariable("clientId") Integer clientId, Model model) throws JsonProcessingException{
		model.addAttribute("bets", bettingRepo.findBetsByClientId(clientId));
		return "betting_summary";
	}
		
	@PostMapping(value = "/summary", headers = "content-type=application/json")  
	public String filterBetsByCriteria(@RequestBody SummaryRequest summaryRequest, Model model) throws JsonProcessingException{
		model.addAttribute("bets", bettingRepo.findBetsByDateRangeAndClientsAndGames(summaryRequest.getStartDate(), summaryRequest.getEndDate(), Arrays.asList(summaryRequest.getClientIds()), Arrays.asList(summaryRequest.getGameNames()), summaryRequest.getLimit(), summaryRequest.getOffset()));
		return "betting_summary";
	}
}