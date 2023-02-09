package com.tomasapp.vivelibre.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDto {
	
	@JsonProperty("auth-vivelibre-token")
	private String authVivelibreToken;
	
	private String date;
	

}
