package com.bozturk.idle.repository.address;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.County;

@Repository("countyRepository")
public interface CountyRepository extends JpaRepository<County, Long>{
	Set<County> findByCityId(Long cityId);

}
