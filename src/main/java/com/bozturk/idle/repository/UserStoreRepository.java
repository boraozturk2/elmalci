package com.bozturk.idle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bozturk.idle.model.UserStore;

public interface UserStoreRepository extends CrudRepository<UserStore, Long> {

    List<UserStore> findByUserId(long userId);

}