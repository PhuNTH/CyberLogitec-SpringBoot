package com.SpringBootApplication.service;

import java.util.List;

import com.SpringBootApplication.model.User;
 
public interface UserService {
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUserById(int id);
    public List<User> selectAllUser();
    public User selectUserById(int id);
	public List<User> checkLogin(User user);
}

