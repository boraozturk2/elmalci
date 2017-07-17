package com.bozturk.idle.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.address.Neighborhood;

@Repository("neighborhoodRepository")
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long>{
	List<Neighborhood> findByAreaId(String areaID);

}
