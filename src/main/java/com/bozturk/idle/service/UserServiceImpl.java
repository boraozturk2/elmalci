package com.bozturk.idle.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bozturk.idle.model.Role;
import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserToken;
import com.bozturk.idle.repository.RoleRepository;
import com.bozturk.idle.repository.UserRepository;
import com.bozturk.idle.repository.UserTokenRepository;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserTokenRepository userTokenRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Kullanıcı Bulunamadı!");
		}
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new UserPrincipal(user.getEmail(), user.getPassword(), user.getName() + " " + user.getLastName(),
				user.isActive(), true, true, true, authorities);
	}

	@Override
	public Role findByRole(String roleName) {
		return roleRepository.findByRole(roleName);
	}

	@Transactional
	@Override
	public UserToken createUserToken(User user) {
		Optional<UserToken> userToken = userTokenRepository.findByUserId(user.getId());
		if (userToken.isPresent())
			userToken.get().updateToken(UUID.randomUUID().toString());
		else
			userToken = Optional.of(new UserToken(UUID.randomUUID().toString(), user));

		return userTokenRepository.save(userToken.get());
	}

	@Transactional
	@Override
	public Optional<UserToken> getUserToken(String token) {
		return userTokenRepository.findByToken(token);
	}
}
