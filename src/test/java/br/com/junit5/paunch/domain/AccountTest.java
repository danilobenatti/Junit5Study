package br.com.junit5.paunch.domain;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static br.com.junit5.paunch.domain.builder.AccountBuilder.oneAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.junit5.paunch.domain.builder.AccountBuilder;
import br.com.junit5.paunch.domain.builder.BuilderMaster;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;

@DisplayName(value = "Domain: Account")
class AccountTest {
	
	@Test
	@DisplayName(value = "Valid 'account' must be created")
	void mustCreateValidAccount() {
		Account account = AccountBuilder.oneAccount().now();
		Assertions.assertAll("Account", () -> assertEquals(1L, account.getId()),
			() -> assertEquals("Valid account", account.getName()),
			() -> assertEquals(oneUser().now(), account.getUser()));
	}
	
	@ParameterizedTest(name = "[{index}] - {3}")
	@DisplayName(value = "Must reject incomplete account")
	@MethodSource(value = "dataProvider")
	void mustRejectIncompleteAccount(Long id, String name, User user,
		String message) {
		AccountBuilder account = oneAccount().withId(id).withName(name)
			.withUser(user);
		String exMessage = assertThrows(ValidationExceptions.class,
			() -> account.now()).getMessage();
		assertEquals(message, exMessage);
	}
	
	private static Stream<Arguments> dataProvider() {
		return Stream.of(
			Arguments.of(1L, null, oneUser().now(), "Name is required"),
			Arguments.of(1L, "Valid account", null, "User is required"));
	}
	
	@Test
	@DisplayName(value = "Generator for 'AccountBuilder'")
	void builderMasterGenerator() {
		BuilderMaster builderMaster = new BuilderMaster();
		builderMaster.gerarCodigoClasse(Account.class);
		assertNotNull(builderMaster);
	}
	
}
