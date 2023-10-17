package br.com.junit5.paunch.service.repository;

import br.com.junit5.paunch.domain.Account;

public interface AccountRepository {
	
	Account save(Account account);
	
}
