package br.com.junit5.paunch.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.service.repository.UserRepository;

class UserServiceTest {
	
	private UserService service;
	
	@Test
	void mustSaveUserWithSuccess() {
		UserRepository repository = Mockito.mock(UserRepository.class);
		service = new UserService(repository);
		Optional<User> user = service.getUserByEmail("user@mail.com");
		Assertions.assertTrue(user.isEmpty());
	}
}
