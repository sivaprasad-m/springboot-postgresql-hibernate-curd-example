package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Integer saveUser(User user) {
		return userRepository.save(user).getId();
	}

	public List<User> getAllUsers() {
		List<User> userList=userRepository.findAll();
		return userList;
	}

	public Optional<User> getUserById(Integer id) {
		Optional<User> op=userRepository.findById(id);
		return op;
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
		
	}

	public boolean isExists(Integer id) {
		
		return userRepository.existsById(id);
	}

}