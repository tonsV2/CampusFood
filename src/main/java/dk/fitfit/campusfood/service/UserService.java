package dk.fitfit.campusfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dk.fitfit.campusfood.model.User;
import dk.fitfit.campusfood.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public User create(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}

	public User login(String email, String password) {
		// TODO: throw user not found exception
		return userRepository.findByEmailAndPassword(email, password);
	}

//	public User update(User user) {
//		return userRepository.save(user);
//	}
//
//	public void deleteUser(long id) {
//		userRepository.delete(id);
//	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
