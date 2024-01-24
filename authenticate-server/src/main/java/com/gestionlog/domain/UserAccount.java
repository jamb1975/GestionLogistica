package com.gestionlog.domain;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserAccount {

	@Id
	@Column(value = "ID")
	private String id;
	
	@Column(value = "USERNAME")
	private String username;
	
	@Column(value = "PASSWORD")
	private String password;

}
