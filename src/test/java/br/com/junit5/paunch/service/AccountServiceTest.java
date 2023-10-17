package br.com.junit5.paunch.service;

import static br.com.junit5.paunch.domain.builder.AccountBuilder.oneAccount;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.service.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
	
	@Mock
	private AccountRepository repository;
	
	@InjectMocks
	private AccountService service;
	
	@Test
	void mustSaveWithSuccess() {
		Account account = oneAccount().withId(null).now();
		
		when(repository.save(account)).thenReturn(oneAccount().now());
		
		Account savedAccount = service.save(account);
		assertNotNull(savedAccount.getId());
	}
}
