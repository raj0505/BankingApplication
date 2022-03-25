package com.amdocs.repository;

import javax.transaction.Transactional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.amdocs.model.Account;


@Repository
@Transactional
public interface AccountRepository extends MongoRepository<Account, Long> {

	public Account findByAccountId(long accountId);
}
