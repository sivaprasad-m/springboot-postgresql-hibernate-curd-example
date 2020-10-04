package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.User;

public interface UserService {
	
	public Integer saveUser(User user);
	public List<User> getAllUsers();
	public  Optional<User> getUserById(Integer id);
    public void deleteUser(Integer id);
    public  boolean isExists(Integer id);
}

