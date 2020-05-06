package com.SpringBootApplication.SpringBootController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

		userServiceImpl.updateUser(user);

		String status = "update user successfully";

		ResponseData responseData = new ResponseData();
		responseData.setUser(user);
		responseData.setStatus(status);

		return responseData;
	}

	@GetMapping("/get-list")
	public @ResponseBody List<User> GetList(){


		List<User> userList=new ArrayList<User>();
		userList = userServiceImpl.selectAllUser();

		return userList;
	}

	@PostMapping("/delete")
	public @ResponseBody String deleteUser(@RequestBody Map<String, Object> DeleteInfo) {

		try {
			userServiceImpl.deleteUserById(Integer.parseInt(DeleteInfo.get("row").toString()));
			return "Delete Successfully";
		}
		catch (Exception e) {
			return "Delete fail";
		}
	}
}
