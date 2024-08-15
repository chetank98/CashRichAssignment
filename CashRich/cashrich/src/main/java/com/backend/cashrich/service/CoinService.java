package com.backend.cashrich.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.cashrich.entity.Coin;
import com.backend.cashrich.entity.User;
import com.backend.cashrich.repository.CoinRespoitory;

@Service
public class CoinService {
	
	@Value("${coinmarketcap.api.key}")
	private String apiKey;
	
	@Autowired
	private CoinRespoitory coinRespoitory;
	
	public String getCoinData(User user, String symbols) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers =  new HttpHeaders();
		headers.set("27ab17d1-215f-49e5-9ca\r\n4-afd48810c149", apiKey);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + symbols;
		
		ResponseEntity<String> response;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, entity , String.class);
		}
		catch(Exception e) {
			throw new RuntimeException("Error while fetching data"+e.getMessage());
		}
		
		Coin coin =  new Coin();
		coin.setUser(user);
		coin.setCoinSymbol(symbols);
		coin.setResponse(response.getBody());
		coin.setTimestamp(LocalDateTime.now());
		
		coinRespoitory.save(coin);
		
		
		return response.getBody();
		
	}
	
}
