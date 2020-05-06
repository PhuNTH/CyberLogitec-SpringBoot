package com.SpringBootApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SpringBootApplication.model.User;
import com.SpringBootApplication.mapper.UserMapper;
 
@Service(value = "userService")
public class UserServiceImpl{
    @Autowired
    UserMapper userMapper;
 
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
 
    @Transactional
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
 
    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }
 
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }
 
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }
    
    public List<User> checkLogin(User user) {
		return userMapper.checkLogin(user);
	}
}
