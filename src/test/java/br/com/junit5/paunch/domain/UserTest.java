package br.com.junit5.paunch.domain;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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
	@DisplayName(value = "Validating 'email' attribute with null value")
	void mustRejectUserWithoutMail() {
		UserBuilder oneUserWithEmailNull = oneUser().withEmail(null);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			oneUserWithEmailNull::now);
		assertEquals("E-Mail is required", ex.getMessage());
	}
	
	@Test
	@DisplayName(value = "Validating 'password' attribute with null value")
	void mustRejectUserWithoutPassword() {
		UserBuilder oneUserWithPasswordNull = oneUser().withPassword(null);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			oneUserWithPasswordNull::now);
		assertEquals("Password is required", ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {4}")
	@DisplayName(value = "Multiple validations with 'null' attribute value")
	@CsvSource(nullValues = "NULL",
		value = { "1, NULL, usermail@email.com, 123456, Name is required",
			"1, Valid User, NULL, 123456, E-Mail is required",
			"1, Valid User, usermail@email.com, NULL, Password is required" })
	void mustRejectUserWithoutAttribute(Long id, String name, String email,
		String password, String message) {
		UserBuilder oneUserWithParamNull = oneUser().withId(id).withName(name)
			.withEmail(email).withPassword(password);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			oneUserWithParamNull::now);
		assertEquals(message, ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {4}")
	@DisplayName(value = "Multiple validations with 'null' attribute value")
	@CsvFileSource(nullValues = "NULL", numLinesToSkip = 1,
		files = "./src/test/resources/mandatoriesUserAttributes.csv")
	void mustRejectUserWithoutAttributeFromCSV(Long id, String name,
		String email, String password, String message) {
		UserBuilder oneUserWithParamNull = oneUser().withId(id).withName(name)
			.withEmail(email).withPassword(password);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			oneUserWithParamNull::now);
		assertEquals(message, ex.getMessage());
	}
	
	@Test
	@DisplayName(value = "Generator for 'UserBuilder'")
	void builderMasterGenerator() {
		BuilderMaster builderMaster = new BuilderMaster();
		builderMaster.gerarCodigoClasse(User.class);
		assertNotNull(builderMaster);
	}
	
}
