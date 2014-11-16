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
		User user = userService.findUserByEmail(name);
		if(user == null) {
			logger.info("user == null");
			// TODO: write proper message
			throw new UsernameNotFoundException("Username " + name + " not found");
		}
		return new SecurityUser(user);
	}

}
