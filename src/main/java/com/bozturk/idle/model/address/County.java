package com.bozturk.idle.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "counties")
public class County {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "county_id")
	private long countyId;

	@Column(name = "city_id")
	@NotEmpty(message = "*CityID Boş olamaz")
	private long cityId;

	@Column(name = "county_name")
	@NotEmpty(message = "*CountyName Kod Boş olamaz")
	private String countyName;

	public County() {
	}

	public long getCountyId() {
		return countyId;
	}

	public void setCountyId(long countyId) {
		this.countyId = countyId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@Override
	public String toString() {
		return "County [countyId=" + countyId + ", cityId=" + cityId + ", countyName=" + countyName + "]";
	}

}