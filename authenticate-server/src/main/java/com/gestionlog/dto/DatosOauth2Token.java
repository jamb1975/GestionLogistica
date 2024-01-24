package com.gestionlog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DatosOauth2Token {
	
	@JsonProperty
	private String accessToken;
	@JsonProperty
	private String expiresIn;

}
