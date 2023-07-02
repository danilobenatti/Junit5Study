package br.com.junit5.paunch.domain;

import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import lombok.Getter;

@Getter
public class User {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	public User(Long id, String name, String email, String password) {
		
		if (name.isBlank())
			throw new ValidationExceptions("Name is required");
		if (email.isBlank())
			throw new ValidationExceptions("E-Mail is required");
		if (password.isBlank())
			throw new ValidationExceptions("Password is required");
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		
	}
	
}
