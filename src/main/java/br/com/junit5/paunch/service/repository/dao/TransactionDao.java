package br.com.junit5.paunch.service.repository.dao;

import br.com.junit5.paunch.domain.Transaction;

public interface TransactionDao {
	
	Transaction save(Transaction transaction);
	
}
