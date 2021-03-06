package dk.fitfit.campusfood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dk.fitfit.campusfood.model.Role;
import dk.fitfit.campusfood.model.User;

public class SecurityUser extends User implements UserDetails {
	private static final long serialVersionUID = 867280338717707274L;

	public SecurityUser(User user) {
		if(user != null)
		{
			this.setId(user.getId());
			this.setName(user.getName());
			this.setEmail(user.getEmail());
			this.setPassword(user.getPassword());
			this.setRoles(user.getRoles());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Set<Role> userRoles = this.getRoles();

		if(userRoles != null)
		{
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
