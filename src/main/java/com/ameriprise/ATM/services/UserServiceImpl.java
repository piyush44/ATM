package com.ameriprise.ATM.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.ameriprise.ATM.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ameriprise.ATM.models.User;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(long userId) {

		return this.userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public void addUser(User user) {
		this.userRepository.save(user);		
	}

}
