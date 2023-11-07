package br.com.junit5.paunch.domain;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Account {
	
	private Long id;
	
	private String name;
	
	private User user;

	public Account(Long id, String name, User user) {
		
		if (StringUtils.isBlank(name))
			throw new ValidationExceptions("Name is required");
		if (ObjectUtils.isEmpty(user))
			throw new ValidationExceptions("User is required");
		
		this.id = id;
		this.name = name;
		this.user = user;
	}
	
}
