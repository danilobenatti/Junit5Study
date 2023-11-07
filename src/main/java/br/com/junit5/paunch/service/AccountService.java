package br.com.junit5.paunch.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.external.AccountEvent;
import br.com.junit5.paunch.service.external.AccountEvent.EventType;
import br.com.junit5.paunch.service.repository.AccountRepository;

public class AccountService {
	
	private AccountRepository repository;
	
	private AccountEvent event;
	
	public AccountService(AccountRepository repository, AccountEvent event) {
		this.repository = repository;
		this.event = event;
	}
	
	public Account save(Account account) {
		List<Account> accountsByUser = repository
			.getAccountsByUser(account.getUser().getId());
		accountsByUser.stream().forEach(i -> {
			if (i.getName().equalsIgnoreCase(account.getName())) {
				throw new ValidationExceptions("Account name is repeated!");
			}
		});
		Account accountSave = repository.save(new Account(account.getId(),
			account.getName().concat(LocalDateTime.now().toString()),
			account.getUser()));
		try {
			event.dispatch(accountSave, EventType.CREATED);
		} catch (Exception e) {
			repository.delete(accountSave);
			throw new RuntimeException("Failed to create account, try again");
		}
		return accountSave;
	}
}
