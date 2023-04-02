package com.prudentgaming.betting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.prudentgaming.betting.model.RestApiResponse;
import com.prudentgaming.betting.model.BettingDataRequest;
import com.prudentgaming.betting.service.BettingService;

@RestController
@RequestMapping("${application.rest.baseurl}")
public class PrudentGamingRestController {
	
	public BettingService service;

	@Qualifier("bettingService")
	@Autowired
	public void setService(BettingService service) {
		this.service = service;
	}
	
	@Value("${local.server.port}")
	private int port;
	
	@Value("${application.rest.rootmessage}")
	private String rootMessage;

	// Get method to perform health check for the rest api
	@GetMapping
	public ResponseEntity<?> root() {
		return ResponseEntity.ok().body(new RestApiResponse(HttpStatus.OK.value(), rootMessage));
	}
	
	// Post method to accept Json data which contains bets, validate request and pass it to the Betting Service for further processing.
	@PostMapping
	public ResponseEntity<?> addBettingData(@RequestHeader(HttpHeaders.CONTENT_TYPE) String contentTypeHeader, final @RequestBody BettingDataRequest bettingDataJson){
		if(!MediaType.APPLICATION_JSON_VALUE.equals(contentTypeHeader))
			return ResponseEntity.badRequest().body(new RestApiResponse(HttpStatus.BAD_REQUEST.value(), "This content-type is not supported"));
		try{
			if(service.addBets(bettingDataJson))
				return ResponseEntity.ok().body(new RestApiResponse(HttpStatus.CREATED.value(), "betting summary data added successfully"));
			else
				return ResponseEntity.ok().body(new RestApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "betting summary data saving failed"));
		}catch(JsonProcessingException jsonEx){
			return ResponseEntity.badRequest().body(new RestApiResponse(HttpStatus.BAD_REQUEST.value(), "Malformed Json"));
		}	
		
	}

}