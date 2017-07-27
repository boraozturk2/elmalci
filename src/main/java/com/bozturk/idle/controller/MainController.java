package com.bozturk.idle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.service.CategoryService;

public class MainController {

	@Autowired
	protected CategoryService categoryService;

	public void addMissingObjects(ModelAndView modelAndView) {
		modelAndView.addObject("categs", categoryService.getCategoryData());
	}

}
