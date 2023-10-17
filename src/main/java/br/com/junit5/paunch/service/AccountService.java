package br.com.junit5.paunch.service;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.service.repository.AccountRepository;

public class AccountService {
	
	private AccountRepository repository;
	
	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}
	
	public Account save(Account account) {
		return repository.save(account);
	}
}
