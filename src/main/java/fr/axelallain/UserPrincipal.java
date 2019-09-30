package fr.axelallain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.axelallain.entity.Utilisateur;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	
	public UserPrincipal(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
	
	public Long getId() {
		return utilisateur.getId();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return utilisateur.getUsername();
	}
	
	public Boolean getStaff() {
		return utilisateur.getStaff();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
