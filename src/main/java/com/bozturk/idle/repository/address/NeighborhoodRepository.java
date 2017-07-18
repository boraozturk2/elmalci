package com.bozturk.idle.repository.address;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.Neighborhood;

@Repository("neighborhoodRepository")
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long>{
	Set<Neighborhood> findByAreaId(Long areaID);

}
