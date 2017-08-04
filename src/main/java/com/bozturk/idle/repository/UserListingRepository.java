package com.bozturk.idle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bozturk.idle.model.UserListing;

public interface UserListingRepository extends JpaRepository<UserListing, Long> {


}