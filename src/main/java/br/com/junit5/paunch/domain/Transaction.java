package br.com.junit5.paunch.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	
	private Long id;
	
	private String description;
	
	private Double value;
	
	private Account account;
	
	private LocalDate date;
	
	private Boolean status;
	
}
