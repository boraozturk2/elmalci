package com.bozturk.idle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.UserBankAccount;

@Transactional
@Repository("userBankRepository")
public interface UserBankAccountRepository extends CrudRepository<UserBankAccount, Long> {

    List<UserBankAccount> findByUserId(long userId);

}