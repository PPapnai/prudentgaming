package com.prudentgaming.betting.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.web.client.HttpClientErrorException;
import com.prudentgaming.betting.repository.BettingDataRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import com.prudentgaming.betting.model.Bet;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PrudentGamingRestControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PrudentGamingRestController prudentRestController;
	
	@Autowired
	private BettingDataRepository bettingRepo;

	@Test
	void contextLoads() {
		assertThat(prudentRestController).isNotNull();
	}
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")
	private int port;
	
	@Value("${application.rest.baseurl}")
	private String baseUrl;
	
	@BeforeAll
	public void setup() throws Exception {
		Bet sampleBet = new Bet();
		sampleBet.setTotalReturns(10);
		when(bettingRepo.save(sampleBet)).thenReturn(sampleBet);
	}

	@Test
	public void testRootSuccess() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + this.port + baseUrl, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.OK);
		assertThat(response.getBody().contains("application is runnning!"));
	}
	
	@Test
	public void testAddBettingDataBadRequest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.BAD_REQUEST);
	}
	
	@Test
    public void testAddBettingDataInvalidHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "random/header");
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
	
	@Test
	public void testAddBettingDataWithNullData() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void testAddBettingDataWithNoData() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		String json = "";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void testAddBettingDataWithEmptyJson() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		String json = "{}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.OK);
	}
	
	@Test
	public void testAddBettingDataSuccess() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		String json = "{\"bets\": [] }";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
		assertResponseHeaderIsCorrect(response, HttpStatus.OK);
	}
	
	private void assertResponseHeaderIsCorrect(ResponseEntity<String> response, HttpStatus expectedStatus) {
		assertThat(response).isNotNull();
		assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(response.getStatusCode().value()).isEqualTo(expectedStatus.value());
	}

}
