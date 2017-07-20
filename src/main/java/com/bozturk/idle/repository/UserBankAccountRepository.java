package com.bozturk.idle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bozturk.idle.model.UserBankAccount;

public interface UserBankAccountRepository extends CrudRepository<UserBankAccount, Long> {

    List<UserBankAccount> findByUserId(long userId);

}