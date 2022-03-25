package com.amdocs.repository;

import javax.transaction.Transactional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.amdocs.model.User;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, Long> {

	public User findByUserId(long userId);
	public User findByName(String name);

}
