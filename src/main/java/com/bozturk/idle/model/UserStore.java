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
	
	@NotEmpty(message = "*İl boş olamaz")
	@Column(name = "city", length=100)
	private String city;
	
	@NotEmpty(message = "*İlçe boş olamaz")
	@Column(name = "county", length=100)
	private String county;
	
	@NotEmpty(message = "*Mahhale/Köy boş olamaz")
	@Column(name = "area", length=100)
	private String area;
	
	@Column(name = "neighborhood", length=100)
	private String neighborhood;
	
	@Column(name = "zipCode", length=100)
	private String zipCode;
	
	@Column(name="neighborhood_id")
	private String neighborhoodId;	
	
	@Column(name = "kapi_no", length=100)
	private String kapiNo;
	
	@Column(name = "sokak", length=100)
	private String sokak;
	
	@Column(name = "cadde", length=100)
	private String cadde;
	
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

	public String getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(String neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getKapiNo() {
		return kapiNo;
	}

	public void setKapiNo(String kapiNo) {
		this.kapiNo = kapiNo;
	}

	public String getSokak() {
		return sokak;
	}

	public void setSokak(String sokak) {
		this.sokak = sokak;
	}

	public String getCadde() {
		return cadde;
	}

	public void setCadde(String cadde) {
		this.cadde = cadde;
	}
	
	

	
}