package dk.fitfit.campusfood.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dk.fitfit.campusfood.config.SecurityConfig;


@Entity
@Table(name="users") // user is a reserved word in postgresql
public class User extends BaseEntity {
	@Transient
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	private String name;

	@Column(nullable=false, unique=true)
	private String email;

	@Column(nullable=false)
	private String password;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String name, String email, String password) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean addRole(Role role)
	{
		return this.getRoles().add(role);
	}
}
