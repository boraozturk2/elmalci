package com.bozturk.idle.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "neighborhood")
public class Neighborhood {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "neighborhood_id")
	private long neighborhoodId;

	@Column(name = "area_id")
	@NotEmpty(message = "*AreaID Boş olamaz")
	private long areaId;

	@Column(name = "neighborhood_name")
	@NotEmpty(message = "*NeighborhoodName Kod Boş olamaz")
	private String neighborhoodName;

	@Column(name = "zip_code")
	@NotEmpty(message = "*PostKodu Boş olamaz")
	private String zipCode;

	public Neighborhood() {
	}

	public long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public String getNeighborhoodName() {
		return neighborhoodName;
	}

	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Neighborhood [neighborhoodId=" + neighborhoodId + ", areaId=" + areaId + ", neighborhoodName="
				+ neighborhoodName + "]";
	}

}
