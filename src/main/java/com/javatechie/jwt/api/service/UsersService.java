package com.javatechie.jwt.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.repository.UserMGDBRepository;
import com.javatechie.jwt.exceptions.BusinessException;

@Service
public class UsersService {
	@Autowired
	UserMGDBRepository repository;

	public List<User> findByAgeGreaterThan(int id) {
		return repository.findByAgeGreaterThan(id);
	}

	public List<User> findByFirstnameLike(String userName) {
		return repository.findByFirstnameLike(userName);
	}

	public User addUser(User user) {
		if(user.getUserName().isEmpty() || user.getUserName().length() ==0)
		{
			throw new BusinessException("601","Input field empty please look into it...");
		}
		return repository.save(user);
	}

	
	
}
