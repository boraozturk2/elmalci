package com.bozturk.idle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.StoreDto;
import com.bozturk.idle.model.UserStore;
import com.bozturk.idle.model.address.Neighborhood;
import com.bozturk.idle.repository.UserRepository;
import com.bozturk.idle.repository.UserStoreRepository;
import com.bozturk.idle.repository.address.AreaRepository;
import com.bozturk.idle.repository.address.CityRepository;
import com.bozturk.idle.repository.address.CountryRepository;
import com.bozturk.idle.repository.address.CountyRepository;
import com.bozturk.idle.repository.address.NeighborhoodRepository;
import com.bozturk.idle.service.StoreService;
import com.bozturk.idle.service.UserPrincipal;

@Controller
public class StoreController extends MainController {

	@Autowired
	private UserStoreRepository userStoreRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountyRepository countyRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreService storeService;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/store", method = RequestMethod.GET)
	public ModelAndView getStore(@RequestParam(required=false) Long userStoreId) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries",countryRepository.findAll());
		
		StoreDto myStore = new StoreDto(); 
		if (userStoreId!=null){
			UserStore userStore = userStoreRepository.findOne(userStoreId);
			myStore.setStoreId(userStore.getStoreId());
			myStore.setStoreName(userStore.getStoreName());
			myStore.setCountryId(userStore.getCountryId());
			myStore.setCityId(userStore.getCityId());
			myStore.setCountyId(userStore.getCountyId());
			myStore.setAreaId(userStore.getAreaId());
			myStore.setNeighborhoodId(userStore.getNeighborhoodId());
			myStore.setCadde(userStore.getCadde());
			myStore.setSokak(userStore.getSokak());
			myStore.setKapi(userStore.getKapi());
			myStore.setApartman(userStore.getApartman());
			myStore.setDaire(userStore.getDaire());
			
			modelAndView.addObject("cities",cityRepository.findByCountryId(userStore.getCountryId()));
			modelAndView.addObject("counties",countyRepository.findByCityId(userStore.getCityId()));
			modelAndView.addObject("areas",areaRepository.findByCountyId(userStore.getCountyId()));
			modelAndView.addObject("neighborhoods",neighborhoodRepository.findByAreaId(userStore.getAreaId()));
		}
		modelAndView.addObject("store", myStore);
		modelAndView.setViewName("/user/store");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/stores", method = RequestMethod.GET)
	public ModelAndView getUserStores() {

		List<UserStore> userStores = storeService.getUserStores();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userStores", userStores);
		modelAndView.setViewName("/user/stores");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/store", method = RequestMethod.POST)
	public ModelAndView upsertCategories(@Valid StoreDto store, BindingResult result, ModelAndView modelAndView) {

		if (result.hasErrors()) {
			modelAndView.getModelMap().addAttribute("errorMessage", "Lütfen Zorunlu Alanları Seçiniz");
			modelAndView.addObject("store", store);
			modelAndView.addObject("countries",countryRepository.findAll());
			if (store.getCountryId()!=null)
				modelAndView.addObject("cities",cityRepository.findByCountryId(store.getCountryId()));
			if (store.getCityId()!=null)
				modelAndView.addObject("counties",countyRepository.findByCityId(store.getCityId()));
			if (store.getCountyId()!=null)
				modelAndView.addObject("areas",areaRepository.findByCountyId(store.getCountyId()));
			if (store.getAreaId()!=null)
				modelAndView.addObject("neighborhoods",neighborhoodRepository.findByAreaId(store.getAreaId()));
			modelAndView.setViewName("/user/store");
			addMissingObjects(modelAndView);
			return modelAndView;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal user = (UserPrincipal)authentication.getPrincipal();
		
		UserStore userStore = null;
		if (store.getStoreId()!=null && store.getStoreId()!=0){
			userStore = userStoreRepository.findOne(store.getStoreId());
		} else {
			userStore = new UserStore();
		}
		
		userStore.setUser(userRepository.findByEmail(user.getUsername()));
		userStore.setStoreName(store.getStoreName());
		userStore.setCountryId(store.getCountryId());
		userStore.setCountry(countryRepository.findOne(store.getCountryId()).getCountryName());
		userStore.setCityId(store.getCityId());
		userStore.setCity(cityRepository.findOne(store.getCityId()).getCityName());
		userStore.setCountyId(store.getCountyId());
		userStore.setCounty(countyRepository.findOne(store.getCountyId()).getCountyName());
		userStore.setAreaId(store.getAreaId());
		userStore.setArea(areaRepository.findOne(store.getAreaId()).getAreaName());
		
		Neighborhood neighborhood = neighborhoodRepository.findOne(store.getNeighborhoodId());
		userStore.setNeighborhoodId(store.getNeighborhoodId());
		userStore.setZipCode(neighborhood.getZipCode());
		userStore.setNeighborhood(neighborhood.getNeighborhoodName());
		
		userStore.setCadde(store.getCadde());
		userStore.setSokak(store.getSokak());
		userStore.setKapi(store.getKapi());
		userStore.setApartman(store.getApartman());
		userStore.setDaire(store.getDaire());
		
		
		userStoreRepository.save(userStore);
		
		/*
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("store", store);
		modelAndView.setViewName("/user/stores");
		addMissingObjects(modelAndView);
		*/
		//return "redirect:/user/stores";
		return getUserStores();
	}

	
}
