package br.com.junit5.paunch.service;

import static br.com.junit5.paunch.domain.builder.AccountBuilder.oneAccount;
import static br.com.junit5.paunch.domain.builder.TransactionBuilder.oneTransaction;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.Transaction;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.repository.dao.TransactionDao;

@EnabledOnJre(value = { JRE.JAVA_11, JRE.JAVA_17, JRE.JAVA_21 })
@EnabledOnOs(value = OS.WINDOWS)
@EnabledIf(value = "isValidHour")
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
	
	@InjectMocks
	private TransactionService service;
	
	@Mock
	private TransactionDao dao;
	
//	@BeforeEach
//	private void checkTime() {
//		Assumptions.assumeTrue(LocalDateTime.now().getHour() < 19);
//	}
	
	@Test
	void mustSaveValidTransaction() {
		Transaction transactionToSave = oneTransaction().withId(null).now();
		Transaction transactionPersisted = oneTransaction().now();
		Mockito.when(dao.save(transactionToSave))
				.thenReturn(transactionPersisted);
		
		Transaction transactionSaved = service.save(transactionToSave);
		Assertions.assertEquals(transactionPersisted, transactionSaved);
		Assertions.assertAll("Transaction",
				() -> assertEquals(1L, transactionSaved.getId()),
				() -> assertEquals("Valid transaction",
						transactionSaved.getDescription()),
				() -> {
					assertAll("Account",
							() -> assertEquals("Valid account",
									transactionSaved.getAccount().getName()),
							() -> assertAll("User",
									() -> assertEquals("Valid User",
											transactionSaved.getAccount()
													.getUser().getName()),
									() -> assertEquals("123456",
											transactionSaved.getAccount()
													.getUser().getPassword())));
				});
	}
	
	@ParameterizedTest(name = "{6}")
	@MethodSource(value = "mandatoryScenarios")
	void mustValidateMandatoryFieldsWhenSaving(Long id, String description,
			Double value, LocalDate date, Account account, Boolean status,
			String message) {
		String exMessage = assertThrows(ValidationExceptions.class, () -> {
			Transaction transaction = oneTransaction().withId(id)
					.withDescription(description).withValue(value)
					.withDate(date).withStatus(status).withAccount(account)
					.now();
			service.save(transaction);
		}).getMessage();
		Assertions.assertEquals(message, exMessage);
	}
	
	static Stream<Arguments> mandatoryScenarios() {
		return Stream.of(
				Arguments.of(1L, null, 10D, LocalDate.now(), oneAccount().now(),
						true, "Description non-existent"),
				Arguments.of(1L, "Description", null, LocalDate.now(),
						oneAccount().now(), true, "Value non-existent"),
				Arguments.of(1L, "Description", 10D, null, oneAccount().now(),
						true, "Date non-existent"),
				Arguments.of(1L, "Description", 10D, LocalDate.now(), null,
						true, "Account non-existent"));
	}
	
	static boolean isValidHour() {
		return LocalDateTime.now().getHour() < 19;
	}
}
