package br.com.junit5.paunch.service.external;

import br.com.junit5.paunch.domain.Account;

public interface AccountEvent {
	
	public enum EventType {CREATED, UPDATE, DELETE}
	
	void dispatch(Account account, EventType type);
	
}
