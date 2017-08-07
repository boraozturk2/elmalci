package com.bozturk.idle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserStore;

@Transactional
@Repository("userStoreRepository")
public interface UserStoreRepository extends CrudRepository<UserStore, Long> {

    List<UserStore> findByUserId(long userId);

}