package br.com.junit5.paunch.service;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserService service;
	
//	@BeforeEach
//	void setup() {
//		MockitoAnnotations.openMocks(this);
//	}
	
//	@AfterEach
//	void tearDown() {
//		verifyNoMoreInteractions(repository);
//	}
	
	@SuppressWarnings("unchecked")
	@Test
	void mustReturnUserByEmail() {
		
		when(repository.getUserByEmail("usermail@email.com")).thenReturn(
			Optional.of(oneUser().now()), Optional.of(oneUser().now()), null);
		
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
	}
	
	@Test
	void mustReturnEmptyWhenUserNotExists() {
		
		when(repository.getUserByEmail("usermail@email.com"))
			.thenReturn(Optional.empty());
		
		Optional<User> user = service.getUserByEmail("usermail@email.com");
		assertTrue(user.isEmpty());
		
		verify(repository).getUserByEmail("usermail@email.com");
	}
	
	@Test
	void mustSaveUserWithSuccess() {
		
		User userToSave = oneUser().withId(null).now();
		
		when(repository.getUserByEmail(userToSave.getEmail()))
			.thenReturn(Optional.empty());
		when(repository.save(userToSave)).thenReturn(oneUser().now());
		
		User savedUser = service.save(userToSave);
		assertNotNull(savedUser.getId());
		
		verify(repository).getUserByEmail(userToSave.getEmail());
		verify(repository).save(userToSave);
	}
	
	@Test
	void mustRejectUserExisting() {
		User userToSave = oneUser().withId(null).now();
		
		when(repository.getUserByEmail(userToSave.getEmail()))
			.thenReturn(Optional.of(oneUser().now()));
		
		ValidationExceptions ex = assertThrows(ValidationExceptions.class,
			() -> service.save(userToSave));
		assertTrue(ex.getMessage().endsWith("already existent"));
		
		verify(repository, never()).save(userToSave);
	}
}
