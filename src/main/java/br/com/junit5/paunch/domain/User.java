package br.com.junit5.paunch.domain;

import org.apache.commons.lang3.StringUtils;

import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
	
	@Exclude
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	public User(Long id, String name, String email, String password) {
		
		if (name == null || name.equals("") || name.trim().equals(""))
			throw new ValidationExceptions("Name is required");
		if (StringUtils.isBlank(email))
			throw new ValidationExceptions("E-Mail is required");
		if (StringUtils.isBlank(password))
			throw new ValidationExceptions("Password is required");
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		
	}
	
}
