package com.bozturk.idle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.model.UserStore;
import com.bozturk.idle.repository.UserStoreRepository;
import com.bozturk.idle.repository.address.CountryRepository;

@Controller
public class StoreController extends MainController {

	@Autowired
	private UserStoreRepository userStoreRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@RequestMapping(value = "/user/store", method = RequestMethod.GET)
	public ModelAndView getCategories() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries",countryRepository.findAll());
		modelAndView.addObject("store", new UserStore());
		modelAndView.setViewName("/user/store");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/store", method = RequestMethod.POST)
	public ModelAndView upsertCategories(@Valid UserStore store, BindingResult result) {

		userStoreRepository.save(store);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("store", new UserStore());
		modelAndView.setViewName("/user/store");
		addMissingObjects(modelAndView);
		return modelAndView;
	}

	
}
