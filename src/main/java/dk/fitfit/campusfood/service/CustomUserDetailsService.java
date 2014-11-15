package dk.fitfit.campusfood.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dk.fitfit.campusfood.SecurityUser;
import dk.fitfit.campusfood.model.Role;
import dk.fitfit.campusfood.model.User;

//TODO: Should this be @Service?
@Component
public class CustomUserDetailsService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		logger.info("name: {}", name);
		User user = userService.findUserByEmail(name);
		if(user == null) {
			logger.info("user == null");
			// TODO: write proper message
			throw new UsernameNotFoundException("Username " + name + " not found");
		}

		logger.info("user.getEmail: {}", user.getEmail());
		logger.info("user.getRoles: {}", user.getRoles());
		for(Role role : user.getRoles())
		{
			logger.info("role.name: {}", role.getName());
		}

		return new SecurityUser(user);
	}

}
