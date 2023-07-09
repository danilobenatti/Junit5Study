package br.com.junit5.paunch.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.domain.builder.UserBuilder;
import br.com.junit5.paunch.infra.UserDummyRepository;

class UserServiceTest {
	
	private UserService service;
	
	@Test
	void mustSaveUserWithSuccess() {
		service = new UserService(new UserDummyRepository());
		User user = UserBuilder.oneUser().withId(null)
			.withEmail("new@email.org").now();
		User saveUser = service.save(user);
		assertNotNull(saveUser.getId());
	}
}
