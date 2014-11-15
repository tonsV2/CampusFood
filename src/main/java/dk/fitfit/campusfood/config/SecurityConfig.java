package dk.fitfit.campusfood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dk.fitfit.campusfood.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private CustomUserDetailsService userDetailsService;

	public SecurityConfig() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>SecurityConfig config loaded!");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.and()
			.formLogin()
//				.and()
//			.logout()
//				.logoutSuccessUrl("/")
//				.permitAll()
				;
	}
}
