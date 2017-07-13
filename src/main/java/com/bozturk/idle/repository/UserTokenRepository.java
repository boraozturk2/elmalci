package com.bozturk.idle.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bozturk.idle.model.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    Optional<UserToken> findByToken(String token);

    Optional<UserToken> findByUserId(long userId);

}