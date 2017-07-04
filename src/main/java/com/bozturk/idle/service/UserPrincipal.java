package com.bozturk.idle.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserPrincipal extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private String userAlias;

	public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserPrincipal(String username, String password, String userAlias,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userAlias = userAlias;
	}

	public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public UserPrincipal(String username, String password, String userAlias, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userAlias = userAlias;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	
	

}
