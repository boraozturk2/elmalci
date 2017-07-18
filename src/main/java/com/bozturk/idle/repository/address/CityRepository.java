package com.bozturk.idle.repository.address;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.City;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Long>{
	Set<City> findByCountryId(Long counrtyId);

}
