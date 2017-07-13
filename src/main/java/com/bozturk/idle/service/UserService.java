package com.bozturk.idle.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.Role;
import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserToken;

public interface UserService {
	
	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public void saveUser(User user);
	public Role findByRole(String roleName);

	@Transactional
	UserToken createUserToken(User user);

	@Transactional
	Optional<UserToken> getUserToken(String token);

}
