package br.com.junit5.paunch.domain;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "id" })
public class Transaction {
	
	private Long id;
	
	private String description;
	
	private Double value;
	
	private Account account;
	
	private LocalDate date;
	
	private Boolean status;
	
}
