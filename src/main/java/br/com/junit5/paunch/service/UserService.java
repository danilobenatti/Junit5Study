package br.com.junit5.paunch.service;

import java.util.Optional;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.domain.exceptions.ValidationExceptions;
import br.com.junit5.paunch.service.repository.UserRepository;

public class UserService {
	
	private UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public User save(User user) {
		repository.getUserByEmail(user.getEmail()).ifPresent(u -> {
			throw new ValidationExceptions(
				String.format("User %s already existent", user.getEmail()));
		});
		return repository.save(user);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return repository.getUserByEmail(email);
	}
	
}
