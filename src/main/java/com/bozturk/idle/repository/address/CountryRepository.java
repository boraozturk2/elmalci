package com.bozturk.idle.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.Country;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Long>{

//	List<Country> findAll();
}
