package com.bozturk.idle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.CategoryDto;
import com.bozturk.idle.service.CategoryService;

@Controller
public class CategoryController extends MainController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView getCategories() {

		CategoryDto categoryDto = categoryService.getCategoryData();

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("categories", categoryDto);
		modelAndView.setViewName("categories");
		addMissingObjects(modelAndView);
		return modelAndView;
	}

	
}
