package com.bozturk.idle.repository.address;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.Area;

@Repository("areaRepository")
public interface AreaRepository extends JpaRepository<Area, Long>{
	Set<Area> findByCountyId(Long countyId);

}
