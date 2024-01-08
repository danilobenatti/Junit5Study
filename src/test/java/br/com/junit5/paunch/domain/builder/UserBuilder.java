package br.com.junit5.paunch.domain.builder;

import br.com.junit5.paunch.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilder {
	
	private User user;
	
	public static UserBuilder oneUser() {
		UserBuilder builder = new UserBuilder();
		initializeDefaultData(builder);
		return builder;
	}
	
	private static void initializeDefaultData(UserBuilder builder) {
		builder.user = new User();
		User user = builder.user;
		
		user.setId(1L);
		user.setName("Valid User");
		user.setEmail("usermail@email.com");
		user.setPassword("123456");
		user.setAge(18);
		
	}
	
	public UserBuilder withId(Long id) {
		user.setId(id);
		return this;
	}
	
	public UserBuilder withName(String name) {
		user.setName(name);
		return this;
	}
	
	public UserBuilder withEmail(String email) {
		user.setEmail(email);
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder withAge(Integer age) {
		user.setAge(age);
		return this;
	}
	
	public User now() {
		return new User(user.getId(), user.getName(), user.getEmail(),
			user.getPassword(), user.getAge());
	}
}
