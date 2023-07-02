package br.com.junit5.paunch.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Domain: User")
class UserTest {
	
	@Test
	@DisplayName(value = "Valid user must be created")
	void createValidUser() {
		User user = new User(1L, "John", "john@mail.com", "123456");
		assertEquals(1L, user.getId());
		assertEquals("John", user.getName());
		assertEquals("john@mail.com", user.getEmail());
		assertEquals("123456", user.getPassword());
	}
}
