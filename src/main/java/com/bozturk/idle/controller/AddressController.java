package com.bozturk.idle.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bozturk.idle.model.address.Area;
import com.bozturk.idle.model.address.City;
import com.bozturk.idle.model.address.County;
import com.bozturk.idle.model.address.Neighborhood;
import com.bozturk.idle.repository.address.AreaRepository;
import com.bozturk.idle.repository.address.CityRepository;
import com.bozturk.idle.repository.address.CountyRepository;
import com.bozturk.idle.repository.address.NeighborhoodRepository;

@Controller
public class AddressController extends MainController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CountyRepository countyRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	
	
	@RequestMapping(value = "/user/cities", method = RequestMethod.GET)
	public @ResponseBody Set<City> getCities(@RequestParam(value = "countryId", required = true) Long countryId) {
		return cityRepository.findByCountryId(countryId);
	}

	@RequestMapping(value = "/user/counties", method = RequestMethod.GET)
	public @ResponseBody Set<County> getCounties(@RequestParam(value = "cityId", required = true) Long cityId) {
		return countyRepository.findByCityId(cityId);
	}
	
	@RequestMapping(value = "/user/areas", method = RequestMethod.GET)
	public @ResponseBody Set<Area> getAreas(@RequestParam(value = "countyId", required = true) Long countyId) {
		return areaRepository.findByCountyId(countyId);
	}
	
	@RequestMapping(value = "/user/neighborhoods", method = RequestMethod.GET)
	public @ResponseBody Set<Neighborhood> getNeighborhoods(@RequestParam(value = "areaId", required = true) Long areaId) {
		return neighborhoodRepository.findByAreaId(areaId);
	}

}
