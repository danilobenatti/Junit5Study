package br.com.junit5.paunch.domain;

import static org.apache.commons.lang3.StringUtils.isBlank;

import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
	
	@Exclude
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Integer age;
	
	public User(Long id, String name, String email, String password, Integer age) {
		
		if (isBlank(name))
			throw new ValidationExceptions("Name is required");
		if (isBlank(email))
			throw new ValidationExceptions("E-Mail is required");
		if (isBlank(password))
			throw new ValidationExceptions("Password is required");
		if(age < 18) {
			throw new ValidationExceptions("He is under age");
		} else {
			System.out.println("Is of legal age");
		}
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		
	}
	
}
