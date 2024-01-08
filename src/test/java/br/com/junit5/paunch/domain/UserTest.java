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
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.junit5.paunch.domain.builder.BuilderMaster;
import br.com.junit5.paunch.domain.builder.UserBuilder;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;

@DisplayName(value = "Domain: User")
class UserTest {
	
	@Test
	@DisplayName(value = "Valid 'user' must be created")
	void createValidUser() {
		User user = oneUser().now();
		System.out.println(user);
		assertAll("User", () -> assertEquals(1L, user.getId()),
				() -> assertEquals("Valid User", user.getName()),
				() -> assertEquals("usermail@email.com", user.getEmail()),
				() -> assertEquals("123456", user.getPassword()),
				() -> assertEquals(18, user.getAge()));
	}
	
	@ParameterizedTest(name = "[{index}] - {0}")
	@DisplayName(value = "Validating 'name' attribute without value")
	@NullAndEmptySource
	@ValueSource(strings = " ")
	void mustRejectUserNameless(String name) {
		UserBuilder oneUserWithoutName = oneUser().withName(name);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
				oneUserWithoutName::now);
		assertEquals("Name is required", ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {0}")
	@DisplayName(value = "Validating 'email' attribute without value")
	@NullAndEmptySource
	@ValueSource(strings = " ")
	void mustRejectUserWithoutMail(String email) {
		UserBuilder oneUserWithoutEmail = oneUser().withEmail(email);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
				oneUserWithoutEmail::now);
		assertEquals("E-Mail is required", ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {0}")
	@DisplayName(value = "Validating 'password' attribute without value")
	@NullAndEmptySource
	@ValueSource(strings = " ")
	void mustRejectUserWithoutPassword(String password) {
		UserBuilder oneUserWithoutPassword = oneUser().withPassword(password);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
				oneUserWithoutPassword::now);
		assertEquals("Password is required", ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {0}")
	@DisplayName(value = "Validating 'age' attribute without value")
	@ValueSource(ints = { Integer.MIN_VALUE, 0 })
	void mustRejectUserWithoutAge(int age) {
		UserBuilder oneUserWithoutAge = oneUser().withAge(age);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
				oneUserWithoutAge::now);
		assertEquals("He is under age", ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {5}")
	@DisplayName(value = "Multiple validations with 'null' attribute value")
	@CsvSource(nullValues = "NULL", value = {
			"1, NULL, usermail@email.com, 123456, 18, Name is required",
			"1, Valid User, NULL, 123456, 18, E-Mail is required",
			"1, Valid User, usermail@email.com, NULL, 18, Password is required",
			"1, Valid User, usermail@email.com, 123456, 0, He is under age" })
	void mustRejectUserWithoutAttribute(Long id, String name, String email,
			String password, int age, String message) {
		UserBuilder oneUserWithParamNull = oneUser().withId(id).withName(name)
				.withEmail(email).withPassword(password).withAge(age);
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
				oneUserWithParamNull::now);
		assertEquals(message, ex.getMessage());
	}
	
	@ParameterizedTest(name = "[{index}] - {5}")
	@DisplayName(value = "Multiple validations with 'null' attribute value")
	@CsvFileSource(nullValues = "NULL", numLinesToSkip = 1,
		files = "./src/test/resources/mandatoriesUserAttributes.csv")
	void mustRejectUserWithoutAttributeFromCSV(Long id, String name,
			String email, String password, int age, String message) {
		UserBuilder oneUserWithParamNull = oneUser().withId(id).withName(name)
				.withEmail(email).withPassword(password).withAge(age);
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
