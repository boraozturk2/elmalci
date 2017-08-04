package com.bozturk.idle.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.CategoryDto;
import com.bozturk.idle.model.Category;
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

	
	@RequestMapping(value = "/user/categories", method = RequestMethod.GET)
	public @ResponseBody Set<Category> getSubCategories(@RequestParam(value = "parentId", required = true) Long parentId) {
		return categoryService.getCategoryDataByParentCategory(parentId);
	}
	
}
