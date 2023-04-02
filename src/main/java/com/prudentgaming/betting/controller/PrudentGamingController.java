package com.prudentgaming.betting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;
import java.util.List;
import com.prudentgaming.betting.service.BettingService;

@Controller
public class PrudentGamingController {
	
	@Autowired
	public BettingService service;
		
		@GetMapping("/bets/")
		public String getBetsByClientId(Model model) {
			model.addAttribute("bets", service.getBets(2));
			return "betting_summary";
		}
		
		@GetMapping("/{clientId}/bet/")
		public String getBetsByClientId(final @PathVariable("clientId") Integer clientId, Model model) throws JsonProcessingException{
			model.addAttribute("bets", service.getBets(clientId));
			return "betting_summary";
		}
}