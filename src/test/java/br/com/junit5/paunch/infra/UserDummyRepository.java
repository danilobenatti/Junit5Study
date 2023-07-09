package br.com.junit5.paunch.infra;

import static br.com.junit5.paunch.domain.builder.UserBuilder.oneUser;

import java.util.Optional;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.service.repository.UserRepository;

public class UserDummyRepository implements UserRepository {
	
	@Override
	public User save(User user) {
		return oneUser().withName(user.getName()).withEmail(user.getEmail())
			.withPassword(user.getPassword()).now();
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		if ("usermail@email.com".equals(email))
			return Optional.of(oneUser().withEmail(email).now());
		return Optional.empty();
	}
	
}
