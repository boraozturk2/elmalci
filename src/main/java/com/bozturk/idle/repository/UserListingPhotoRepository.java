package com.bozturk.idle.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserListingPhoto;

@Transactional
@Repository("userListingPhotoRepository")
public interface UserListingPhotoRepository extends JpaRepository<UserListingPhoto, Long> {

	@Query("from UserListingPhoto where listing_id=:listingId")
	Set<UserListingPhoto> findByListing(@Param("listingId") long listingId);
	UserListingPhoto findByListingIdAndPorder(long listingId, int porder);

}