package com.bozturk.idle.dto;

import java.util.List;

import com.bozturk.idle.model.address.Country;

public class AddressDto {

	private List<Country> countries;

	public AddressDto() {
		// TODO Auto-generated constructor stub
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	

}
