package com.bozturk.idle.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserToken;

@Transactional
@Repository("userTokenRepository")
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    Optional<UserToken> findByToken(String token);

    Optional<UserToken> findByUserId(long userId);

}