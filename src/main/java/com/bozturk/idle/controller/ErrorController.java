package com.bozturk.idle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController extends MainController {

	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView getStore() {

		ModelAndView modelAndView = new ModelAndView();
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	
	
}
