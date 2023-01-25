package com.javatechie.jwt.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.javatechie.jwt.api.entity.User;
@Repository
public interface UserMGDBRepository extends MongoRepository<User,Integer>{
	 User findByUserName(String username);
     @Query(value = "{'id': {$gt: ?0}}")
     List<User> findByAgeGreaterThan(int  id);
     @Query("{ 'userName' : { $regex: ?0 } }")
	 List<User> findByFirstnameLike(String userName);
}

