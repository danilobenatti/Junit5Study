package br.com.junit5.paunch.domain.builder;

import java.time.LocalDate;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.Transaction;

public class TransactionBuilder {
	private Transaction elemento;
	private TransactionBuilder(){}

	public static TransactionBuilder umTransaction() {
		TransactionBuilder builder = new TransactionBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	public static void inicializarDadosPadroes(TransactionBuilder builder) {
		builder.elemento = new Transaction();
		Transaction elemento = builder.elemento;

		
		elemento.setId(0L);
		elemento.setDescription("");
		elemento.setValue(0.0);
		elemento.setAccount(null);
		elemento.setDate(null);
		elemento.setStatus(false);
	}

	public TransactionBuilder comId(Long param) {
		elemento.setId(param);
		return this;
	}

	public TransactionBuilder comDescription(String param) {
		elemento.setDescription(param);
		return this;
	}

	public TransactionBuilder comValue(Double param) {
		elemento.setValue(param);
		return this;
	}

	public TransactionBuilder comAccount(Account param) {
		elemento.setAccount(param);
		return this;
	}

	public TransactionBuilder comDate(LocalDate param) {
		elemento.setDate(param);
		return this;
	}

	public TransactionBuilder comStatus(Boolean param) {
		elemento.setStatus(param);
		return this;
	}

	public Transaction agora() {
		return elemento;
	}
}
