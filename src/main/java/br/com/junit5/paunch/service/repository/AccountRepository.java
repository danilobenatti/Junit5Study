package br.com.junit5.paunch.service.repository;

import java.util.List;

import br.com.junit5.paunch.domain.Account;

public interface AccountRepository {
	
	Account save(Account account);
	
	List<Account> getAccountsByUser(Long userId);
	
}
