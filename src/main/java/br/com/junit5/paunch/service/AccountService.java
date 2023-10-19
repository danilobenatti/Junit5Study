package br.com.junit5.paunch.service;

import java.util.List;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.repository.AccountRepository;

public class AccountService {
	
	private AccountRepository repository;
	
	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}
	
	public Account save(Account account) {
		List<Account> accountsByUser = repository
			.getAccountsByUser(account.getUser().getId());
		accountsByUser.stream().forEach(i -> {
			if (i.getName().equalsIgnoreCase(account.getName())) {
				throw new ValidationExceptions("Account name is repeated!");
			}
		});
		return repository.save(account);
	}
}
