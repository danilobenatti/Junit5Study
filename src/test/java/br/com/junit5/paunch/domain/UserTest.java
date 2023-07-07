package br.com.junit5.paunch.domain;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.junit5.paunch.domain.builder.BuilderMaster;
import br.com.junit5.paunch.domain.builder.UserBuilder;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;

@DisplayName(value = "Domain: User")
class UserTest {
	
	@Test
	@DisplayName(value = "Valid 'user' must be created")
	void createValidUser() {
		User user = oneUser().now();
		assertAll("User", () -> assertEquals(1L, user.getId()),
			() -> assertEquals("Valid User", user.getName()),
			() -> assertEquals("usermail@email.com", user.getEmail()),
			() -> assertEquals("123456", user.getPassword()));
	}
	
	@Test
	@DisplayName(value = "Validating 'name' attribute with null value")
	void mustRejectUserNameless() {
		UserBuilder oneUserWithNameNull = oneUser().withName(null);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			oneUserWithNameNull::now);
		assertEquals("Name is required", ex.getMessage());
	}
	
	@Test
	@DisplayName(value = "Generator for 'UserBuilder'")
	void builderMasterGenerator() {
		BuilderMaster builderMaster = new BuilderMaster();
		builderMaster.gerarCodigoClasse(User.class);
		Assertions.assertNotNull(builderMaster);
	}
	
}
