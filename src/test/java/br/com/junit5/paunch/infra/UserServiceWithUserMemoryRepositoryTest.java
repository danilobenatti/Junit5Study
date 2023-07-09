package br.com.junit5.paunch.infra;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.UserService;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserServiceWithUserMemoryRepositoryTest {
	
	private static UserService service = new UserService(
		new UserMemoryRepository());
	
	@Test
	@Order(value = 1)
	void mustSalveValidUser() {
		User user = service.save(oneUser().withId(null).now());
		assertNotNull(user.getId());
	}
	
	@Test
	@Order(value = 2)
	void mustRejectExistingUser() {
		User user = oneUser().withId(null).now();
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			() -> service.save(user));
		assertEquals("User usermail@email.com already exists", ex.getMessage());
	}
	
}
