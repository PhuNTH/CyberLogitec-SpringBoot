package com.SpringBootApplication.SpringBootController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringBootApplication.model.User;
import com.SpringBootApplication.service.UserServiceImpl;

@Controller
public class DemoController {
	User recentUser;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Welcome() {
		return "login";
	}

	@Autowired
	UserServiceImpl userServiceImpl;

	@RequestMapping("/user-list")
	public ModelAndView UserList() {
		List<User> listUser = userServiceImpl.selectAllUser();
		ModelAndView modelView = new ModelAndView("userList");
		modelView.addObject("users",listUser);
		return modelView;
	}

	@PostMapping(path="/check-login")
	public String CheckLogin(User user) {
		List<User> listUser = new ArrayList<User>();
		listUser = userServiceImpl.checkLogin(new User(user.getUsername(),user.getPassword()));
		System.out.println("\n"+user.getUsername());
		System.out.println("\n"+user.getEmail());
		System.out.println("\n"+user.getPassword()+"\n");
		if(!listUser.isEmpty()) {
			//return "redirect:/user-list";
			return "redirect:/show-webix-table";
		}
		else {
			System.out.println("Login Fail!!!!!");
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/show-webix-table", method = RequestMethod.GET)
	public String ShowWebixTable(){
		return "webixTable";
	}

	@RequestMapping(value = "/register")
	public String ShowRegister(Model model) {
		if(recentUser!=null)
			model.addAttribute("user",recentUser);
		else
			model.addAttribute("user",new User(0,"","",""));

		return "userRegister";
	}

	@PostMapping("/check-register")
	public String CheckRegister(User user) {
		System.out.println("\n"+user.getUsername());
		System.out.println("\n"+user.getEmail());
		System.out.println("\n"+user.getPassword()+"\n");
		userServiceImpl.insertUser(user);
		return "redirect:/";
	}

	@RequestMapping("/delete-user/{id}")
	public String DeleteUser(@PathVariable("id") int id) {
		userServiceImpl.deleteUserById(id);
		return "redirect:/user-list";
	}

	@RequestMapping("/edit-user/{id}")
	public ModelAndView EditUser(@PathVariable("id") int id) {
		User selectedUser = userServiceImpl.selectUserById(id);
		ModelAndView modelView = new ModelAndView("userEdit");
		modelView.addObject("user",selectedUser);
		return modelView;
	}

	@PostMapping("/add-user")
	public String AddUser() {
		return "userAdd";
	}

	@PostMapping("/check-add")
	public String CheckAdd(User user) {
		System.out.println("\n"+user.getUsername());
		System.out.println("\n"+user.getEmail());
		System.out.println("\n"+user.getPassword()+"\n");
		userServiceImpl.insertUser(user);
		return "redirect:/user-list";
	}
}
