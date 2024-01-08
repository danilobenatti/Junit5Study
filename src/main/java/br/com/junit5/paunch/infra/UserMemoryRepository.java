package br.com.junit5.paunch.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import br.com.junit5.paunch.domain.User;
import br.com.junit5.paunch.service.repository.UserRepository;

public class UserMemoryRepository implements UserRepository {
	
	private List<User> users;
	
	private Long currentId;
	
	protected static Logger log = LogManager.getLogger();
	
	public UserMemoryRepository() {
		currentId = 0L;
		users = new ArrayList<>();
		save(new User(null, "User #1", "user1@mail.com", "123456", 18));
	}
	
	@Override
	public User save(User user) {
		User newUser = new User(nextId(), user.getName(), user.getEmail(),
				user.getPassword(), user.getAge());
		users.add(newUser);
		return newUser;
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		return users.stream().filter(u -> u.getEmail().equalsIgnoreCase(email))
				.findFirst();
	}
	
	private Long nextId() {
		return ++currentId;
	}
	
	public static void main(String[] args) {
		UserMemoryRepository repo = new UserMemoryRepository();
		repo.save(new User(null, "User #2", "user2@mail.com", "123456", 18));
		repo.showUsers();
	}
	
	void showUsers() {
		Configurator.initialize(UserMemoryRepository.class.getName(),
				"./src/main/resources/log4j2.properties");
		log.info(users);
	}
	
}
