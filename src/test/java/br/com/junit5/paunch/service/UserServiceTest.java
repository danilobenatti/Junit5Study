package br.com.junit5.paunch.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
	
	@SuppressWarnings("unchecked")
	@Test
	void mustReturnUserByEmail() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		service = new UserService(repository);
		
		when(repository.getUserByEmail("usermail@email.com")).thenReturn(
			Optional.of(UserBuilder.oneUser().now()),
			Optional.of(UserBuilder.oneUser().now()), null);
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		System.out.println(user);
		assertTrue(user.isPresent());
		user = service.getUserByEmail("usermail123456@email.com");
		System.out.println(user);
		user = service.getUserByEmail("usermail@email.com");
		System.out.println(user);
		user = service.getUserByEmail("usermail@email.com");
		System.out.println(user);
		
		/**
		 * verify(mock, times(5)).someMethod("was called five times");
		 * verify(mock, never()).someMethod("was never called");
		 * verify(mock, atLeastOnce()).someMethod("was called at least once");
		 * verify(mock, atLeast(2)).someMethod("was called at least twice");
		 * verify(mock, atMost(3)).someMethod("was called at most 3 times");
		 */
		verify(repository, times(3)).getUserByEmail("usermail@email.com");
		verify(repository, times(1)).getUserByEmail("usermail123456@email.com");
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
