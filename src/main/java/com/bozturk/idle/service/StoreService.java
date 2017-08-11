package com.bozturk.idle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserStore;
import com.bozturk.idle.repository.UserRepository;
import com.bozturk.idle.repository.UserStoreRepository;

@Transactional
@Service("storeService")
public class StoreService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserStoreRepository storeRepository;

	public List<UserStore> getUserStores() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPr = (UserPrincipal)authentication.getPrincipal();
		
		User user = userRepository.findByEmail(userPr.getUsername());
		List<UserStore> userStores = storeRepository.findByUserId(user.getId());
		return userStores;
	}

}
