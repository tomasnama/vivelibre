package com.tomasapp.vivelibre.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tomasapp.vivelibre.dto.ResultDto;
import com.tomasapp.vivelibre.dto.TokenDto;
import com.tomasapp.vivelibre.service.ApiTokenService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tnavarro
 *
 */
@RestController
@Slf4j
public class TokenController {

	@Autowired
	ApiTokenService apiTokenService;

	@GetMapping(value = "/get-token")
	public ResponseEntity<ResultDto> getToken() {
		TokenDto token = apiTokenService.getToken();
		try {
			// February 21, 2018
			Date now = Calendar.getInstance().getTime();
			String pattern = "MMMMM dd, yyyy";
			DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResultDto.builder().authVivelibreToken(token.getToken()).date(df.format(now)).build());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}
}
