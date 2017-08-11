package com.bozturk.idle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserListing;

@Transactional
@Repository("userListingRepository")
public interface UserListingRepository extends JpaRepository<UserListing, Long> {

	List<UserListing> findByUserId(long userId);	
}