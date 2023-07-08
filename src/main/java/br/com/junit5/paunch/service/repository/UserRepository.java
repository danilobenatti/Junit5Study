package br.com.junit5.paunch.service.repository;

import java.util.Optional;

import br.com.junit5.paunch.domain.User;

public interface UserRepository {
	
	User save(User user);
	
	Optional<User> getUserByEmail(String email);
	
}
