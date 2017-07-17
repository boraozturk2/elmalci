package com.bozturk.idle.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.City;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Long>{
	List<City> findByCountryId(String counrtyID);

}
