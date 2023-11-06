package br.com.junit5.paunch.service;

import static br.com.junit5.paunch.domain.builder.AccountBuilder.oneAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.external.AccountEvent;
import br.com.junit5.paunch.service.external.AccountEvent.EventType;
import br.com.junit5.paunch.service.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class AccountServiceTest {
	
	@Mock
	private AccountEvent event;
	
	@Mock
	private AccountRepository repository;
	
	@InjectMocks
	private AccountService service;
	
	@Test
	void mustSaveFirstAccountWithSuccess() throws Exception {
		Account account = oneAccount().withId(null).now();
		
		when(repository.save(account)).thenReturn(oneAccount().now());
		
		doNothing().when(event).dispatch(oneAccount().now(), EventType.CREATED);
		
		Account savedAccount = service.save(account);
		assertNotNull(savedAccount.getId());
	}
	
	@Test
	void mustSaveSecondAccountWithSuccess() {
		Account account = oneAccount().withId(null).now();
		
		when(repository.getAccountsByUser(account.getUser().getId()))
			.thenReturn(
				Arrays.asList(oneAccount().withName("Other account").now()));
		when(repository.save(account)).thenReturn(oneAccount().now());
		
		Account savedAccount = service.save(account);
		assertNotNull(savedAccount.getId());
	}
	
	@Test
	void mustRejectAccountDuplicate() {
		Account account = oneAccount().withId(null).now();
		
		when(repository.getAccountsByUser(account.getUser().getId()))
			.thenReturn(Arrays.asList(oneAccount().now()));
		
//		when(repository.save(account)).thenReturn(oneAccount().now());
		
		String message = assertThrows(ValidationExceptions.class,
			() -> service.save(account)).getMessage();
		
		assertEquals("Account name is repeated!", message);
	}
	
	@Test
	void shouldNotKeepCountWithoutEvent() throws Exception {
		Account account = oneAccount().withId(null).now();
		
		Account oneAccountNow = oneAccount().now();
		when(repository.save(account)).thenReturn(oneAccountNow);
		
		doThrow(new Exception("Catastrophic failure")).when(event)
			.dispatch(oneAccountNow, EventType.CREATED);
		
		String message = assertThrows(Exception.class,
			() -> service.save(account)).getMessage();
		
		assertEquals("Failed to create account, try again", message);
		
		verify(repository).delete(oneAccountNow);
	}
}
