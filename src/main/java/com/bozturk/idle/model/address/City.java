package com.bozturk.idle.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "city_id")
	private long cityId;

	@Column(name = "country_id")
	@NotEmpty(message = "*CountryID Boş olamaz")
	private long countryId;

	@Column(name = "city_name")
	@NotEmpty(message = "*CityName Kod Boş olamaz")
	private String cityName;

	@Column(name = "plate_no")
	@NotEmpty(message = "*PlateNo Kod Boş olamaz")
	private String plateNo;

	@Column(name = "phone_code")
	@NotEmpty(message = "*PhoneCode Kod Boş olamaz")
	private String phoneCode;

	public City() {
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", countryId=" + countryId + ", cityName=" + cityName + ", plateNo=" + plateNo
				+ ", phoneCode=" + phoneCode + "]";
	}

}
