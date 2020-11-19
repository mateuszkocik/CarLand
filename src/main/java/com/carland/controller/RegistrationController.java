package com.carland.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.carland.entity.User;
import com.carland.service.UserService;
import com.carland.user.CrmUser;

@Controller
public class RegistrationController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/registration")
	public String showRegistrationPage(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUser(
			@Valid @ModelAttribute("crmUser") CrmUser theUser,
			BindingResult theBindingResult, 
			HttpServletRequest request,
			Model theModel) {
		
		String username = theUser.getUsername();
		
		if (theBindingResult.hasErrors()){
			 return "registration";
	        }
		
		if(userService.findByUsername(username) != null) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User with that email already exists.");
			return "registration";
		}
		userService.saveUser(theUser);
		
		authenticateUserAfterRegistration(theUser.getUsername(), theUser.getPassword(), request);
		
		return "redirect:/";
	}
	
	
	void authenticateUserAfterRegistration(String username, String password, HttpServletRequest request) {
		try {
			request.login(username,password);
			User theUser = userService.findByUsername(username);
			HttpSession session = request.getSession();
			session.setAttribute("user", theUser);
			
		} catch (ServletException e) { 
			e.printStackTrace();
		}
	}
	
}
