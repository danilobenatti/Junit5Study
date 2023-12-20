package br.com.junit5.paunch.domain.builder;

import java.time.LocalDate;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionBuilder {
	
	private Transaction transaction;
	
	public static TransactionBuilder oneTransaction() {
		TransactionBuilder builder = new TransactionBuilder();
		initializeDefaultData(builder);
		return builder;
	}

	public static void initializeDefaultData(TransactionBuilder builder) {
		builder.transaction = new Transaction();
		Transaction transaction = builder.transaction;

		
		transaction.setId(1L);
		transaction.setDescription("Valid transaction");
		transaction.setValue(10.0);
		transaction.setAccount(AccountBuilder.oneAccount().now());
		transaction.setDate(LocalDate.now());
		transaction.setStatus(false);
	}

	public TransactionBuilder withId(Long id) {
		transaction.setId(id);
		return this;
	}

	public TransactionBuilder withDescription(String description) {
		transaction.setDescription(description);
		return this;
	}

	public TransactionBuilder withValue(Double value) {
		transaction.setValue(value);
		return this;
	}

	public TransactionBuilder withAccount(Account account) {
		transaction.setAccount(account);
		return this;
	}

	public TransactionBuilder withDate(LocalDate date) {
		transaction.setDate(date);
		return this;
	}

	public TransactionBuilder withStatus(Boolean status) {
		transaction.setStatus(status);
		return this;
	}

	public Transaction now() {
		return transaction;
	}
}
