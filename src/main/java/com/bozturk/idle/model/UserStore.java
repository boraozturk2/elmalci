package com.bozturk.idle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "user_stores")
public class UserStore {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="store_id")
	private long storeId;
	
	@Column(name = "store_name", length=100)
	@NotEmpty(message = "*İsim boş olamaz")
	private String storeName;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	@NotEmpty(message = "*Ülke boş olamaz")
	@Column(name = "country", length=100)
	private String country;
	
	@Column(name = "country_id", length=63)
	private Long countryId;
	
	@NotEmpty(message = "*İl boş olamaz")
	@Column(name = "city", length=100)
	private String city;
	
	@Column(name = "city_id", length=36)
	private Long cityId;
	
	@NotEmpty(message = "*İlçe boş olamaz")
	@Column(name = "county", length=100)
	private String county;
	
	@Column(name = "county_id", length=36)
	private Long countyId;
	
	@NotEmpty(message = "*Mahhale/Köy boş olamaz")
	@Column(name = "area", length=100)
	private String area;
	
	@Column(name = "area_id", length=36)
	private Long areaId;
	
	@Column(name = "neighborhood", length=100)
	private String neighborhood;
	
	@Column(name = "neighborhood_id", length=36)
	private Long neighborhoodId;
	
	@Column(name = "zipCode", length=100)
	private String zipCode;
	
	@Column(name = "cadde", length=100)
	private String cadde;
	
	@Column(name = "sokak", length=100)
	private String sokak;
	
	@Column(name = "kapi", length=100)
	private String kapi;
	
	@Column(name = "apartman", length=100)
	private String apartman;
	
	@Column(name = "daire", length=20)
	private String daire;
	
	
	public UserStore() {
		// TODO Auto-generated constructor stub
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(Long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getCadde() {
		return cadde;
	}

	public void setCadde(String cadde) {
		this.cadde = cadde;
	}

	public String getSokak() {
		return sokak;
	}

	public void setSokak(String sokak) {
		this.sokak = sokak;
	}

	public String getKapi() {
		return kapi;
	}

	public void setKapi(String kapi) {
		this.kapi = kapi;
	}

	public String getApartman() {
		return apartman;
	}

	public void setApartman(String apartman) {
		this.apartman = apartman;
	}

	public String getDaire() {
		return daire;
	}

	public void setDaire(String daire) {
		this.daire = daire;
	}
	
}