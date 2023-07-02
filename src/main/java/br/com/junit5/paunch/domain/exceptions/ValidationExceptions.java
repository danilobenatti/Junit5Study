package br.com.junit5.paunch.domain.exceptions;

public class ValidationExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ValidationExceptions(String message) {
		super(message);
	}
	
}
