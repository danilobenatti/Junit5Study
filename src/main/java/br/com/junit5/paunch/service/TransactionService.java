package br.com.junit5.paunch.service;

import java.time.LocalDateTime;

import br.com.junit5.paunch.domain.Transaction;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.repository.dao.TransactionDao;

public class TransactionService {
	
	private TransactionDao dao;
	
	public Transaction save(Transaction transaction) {
		if (LocalDateTime.now().getHour() > 19) {
			throw new ValidationExceptions("Out-of-hours transaction!");
		}
		if (transaction.getDescription() == null) {
			throw new ValidationExceptions("Description non-existent");
		}
		if (transaction.getValue() == null) {
			throw new ValidationExceptions("Value non-existent");
		}
		if (transaction.getAccount() == null) {
			throw new ValidationExceptions("Account non-existent");
		}
		if (transaction.getDate() == null) {
			throw new ValidationExceptions("Date non-existent");
		}
		if (transaction.getStatus() == null) {
			transaction.setStatus(false);
		}
		return dao.save(transaction);
	}
	
}
