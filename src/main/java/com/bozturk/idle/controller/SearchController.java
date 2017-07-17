package com.bozturk.idle.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.model.Role;
import com.bozturk.idle.model.User;
import com.bozturk.idle.service.UserService;

@Controller
public class SearchController extends MainController {
	
	@Autowired
	private UserService userService;

	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView createNewUser(@RequestParam String searchText, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("successMessage", "User has been registered successfully");
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("search");
		addMissingObjects(modelAndView);
		return modelAndView;
	}


}
