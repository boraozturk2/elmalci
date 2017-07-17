package com.bozturk.idle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozturk.idle.repository.address.AreaRepository;
import com.bozturk.idle.repository.address.CityRepository;
import com.bozturk.idle.repository.address.CountryRepository;
import com.bozturk.idle.repository.address.CountyRepository;
import com.bozturk.idle.repository.address.NeighborhoodRepository;

@Service("addressService")
public class AddressService {

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
	

}
