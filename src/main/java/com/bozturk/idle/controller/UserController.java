package com.bozturk.idle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.model.User;
import com.bozturk.idle.service.UserService;

@Controller
public class UserController extends MainController {
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/main", method = RequestMethod.GET)
	public ModelAndView createNewUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/main");
		addMissingObjects(modelAndView);
		return modelAndView;
	}


}
