package br.com.junit5.paunch.domain.builder;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;

import br.com.junit5.paunch.domain.Account;
import br.com.junit5.paunch.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountBuilder {
	
	private Account account;
	
	public static AccountBuilder oneAccount() {
		AccountBuilder builder = new AccountBuilder();
		initializeDefaultData(builder);
		return builder;
	}
	
	private static void initializeDefaultData(AccountBuilder builder) {
		builder.account = new Account();
		Account account = builder.account;
		
		account.setId(1L);
		account.setName("Valid account");
		account.setUser(oneUser().now());
		
	}
	
	public AccountBuilder withId(Long id) {
		account.setId(id);
		return this;
	}
	
	public AccountBuilder withName(String name) {
		account.setName(name);
		return this;
	}
	
	public AccountBuilder withUser(User user) {
		account.setUser(user);
		return this;
	}
	
	public Account now() {
		return new Account(account.getId(), account.getName(),
			account.getUser());
	}
}
