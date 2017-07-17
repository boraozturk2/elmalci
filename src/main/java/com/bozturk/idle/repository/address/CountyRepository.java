package com.bozturk.idle.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.County;

@Repository("countyRepository")
public interface CountyRepository extends JpaRepository<County, Long>{
	List<County> findByCityId(String cityID);

}
