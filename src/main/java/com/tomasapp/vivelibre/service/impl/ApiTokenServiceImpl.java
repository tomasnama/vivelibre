package com.tomasapp.vivelibre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tomasapp.vivelibre.constant.Const;
import com.tomasapp.vivelibre.dto.TokenDto;
import com.tomasapp.vivelibre.dto.UserDto;
import com.tomasapp.vivelibre.service.ApiTokenService;

/**
 * @author tnavarro
 *
 */
@Service
public class ApiTokenServiceImpl implements ApiTokenService {

	@Autowired
	private  RestTemplate restTemplate;
	
	@Override
	public TokenDto getToken() {
		
		HttpEntity<UserDto> request = new HttpEntity<>(UserDto.builder()
				.username(Const.USER)
				.password(Const.PASS)
				.build());
		ResponseEntity<TokenDto> response = restTemplate.exchange(
				Const.URI_TOKEN, HttpMethod.POST, request,
			    TokenDto.class);
		return response.getBody();
		
	}

}
