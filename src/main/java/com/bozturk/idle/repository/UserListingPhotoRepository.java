package com.bozturk.idle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserListingPhoto;

@Transactional
@Repository("userListingPhotoRepository")
public interface UserListingPhotoRepository extends JpaRepository<UserListingPhoto, Long> {


}