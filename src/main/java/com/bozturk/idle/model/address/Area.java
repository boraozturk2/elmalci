package com.bozturk.idle.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_id")
	private long areaId;

	@Column(name = "county_id")
	@NotEmpty(message = "*CountyID Boş olamaz")
	private long countyId;

	@Column(name = "area_name")
	@NotEmpty(message = "*AreaName Kod Boş olamaz")
	private String areaName;

	public Area() {
	}
 
	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public long getCountyId() {
		return countyId;
	}

	public void setCountyId(long countyId) {
		this.countyId = countyId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", countyId=" + countyId + ", areaName=" + areaName + "]";
	}

}
