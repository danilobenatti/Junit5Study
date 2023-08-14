package br.com.junit5.paunch.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
		
		when(repository.getUserByEmail("usermail@email.com"))
			.thenReturn(Optional.of(UserBuilder.oneUser().now()));
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		assertTrue(user.isPresent());
		System.out.println(user);
		
		/**
		 * verify(mock, times(5)).someMethod("was called five times");
		 * verify(mock, never()).someMethod("was never called");
		 * verify(mock, atLeastOnce()).someMethod("was called at least once");
		 * verify(mock, atLeast(2)).someMethod("was called at least twice");
		 * verify(mock, atMost(3)).someMethod("was called at most 3 times");
		 */
		verify(repository, atLeastOnce()).getUserByEmail("usermail@email.com");
		verify(repository, never()).getUserByEmail("othermail@email.com");
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void mustReturnEmptyWhenUserNotExists() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		service = new UserService(repository);
		
		when(repository.getUserByEmail("usermail@email.com"))
			.thenReturn(Optional.empty());
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		assertTrue(user.isEmpty());
	}
	
}
