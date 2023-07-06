package br.com.junit5.paunch.domain.builder;

import br.com.junit5.paunch.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilder {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	public static UserBuilder oneUser() {
		UserBuilder builder = new UserBuilder();
		initializeDefaultData(builder);
		return builder;
	}
	
	private static void initializeDefaultData(UserBuilder builder) {
		builder.id = 1L;
		builder.name = "Valid User";
		builder.email = "usermail@email.com";
		builder.password = "123456";
	}
	
	public UserBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public User now() {
		return new User(id, name, email, password);
	}
}
