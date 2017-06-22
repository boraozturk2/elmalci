package com.bozturk.idle.service;

import com.bozturk.idle.model.Role;
import com.bozturk.idle.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public Role findByRole(String roleName);
}
