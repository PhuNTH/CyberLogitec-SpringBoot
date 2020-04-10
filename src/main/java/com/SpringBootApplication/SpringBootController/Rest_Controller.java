package com.SpringBootApplication.SpringBootController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApplication.model.ResponseData;
import com.SpringBootApplication.model.User;
import com.SpringBootApplication.service.UserServiceImpl;

@RestController
public class Rest_Controller {
	@Autowired
	UserServiceImpl userServiceImpl;
	public Rest_Controller() throws IOException {
		System.out.println("Rest bean has created");
	}
	
	@PostMapping("/check-edit")
	public @ResponseBody ResponseData ProcessChanges(@RequestBody User user){

		userServiceImpl.insertUser(user);

		String status = "update user successfully";

		ResponseData responseData = new ResponseData();
		responseData.setUser(user);
		responseData.setStatus(status);

		return responseData;

	}
}
