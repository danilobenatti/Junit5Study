package br.com.junit5.paunch.service;

import static br.com.junit5.paunch.domain.builder.TransactionBuilder.oneTransaction;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.domain.Transaction;
import br.com.junit5.paunch.service.repository.dao.TransactionDao;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
	
	@InjectMocks
	private TransactionService service;
	
	@Mock
	private TransactionDao dao;
	
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
}
