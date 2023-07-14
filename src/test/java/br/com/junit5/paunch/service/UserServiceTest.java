package br.com.junit5.paunch.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.domain.builder.UserBuilder;
import br.com.junit5.paunch.service.repository.UserRepository;

class UserServiceTest {
	
	private UserService service;
	
	@Test
	void mustReturnUserByEmail() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		service = new UserService(repository);
		
		Mockito.when(repository.getUserByEmail("usermail@email.com"))
			.thenReturn(Optional.of(UserBuilder.oneUser().now()));
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		Assertions.assertFalse(user.isEmpty());
		System.out.println(user);
	}
	
	@Test
	void mustReturnEmptyWhenUserNotExists() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		service = new UserService(repository);
		
		Mockito.when(repository.getUserByEmail("usermail@email.com"))
			.thenReturn(Optional.empty());
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		Assertions.assertTrue(user.isEmpty());
	}
	
}
