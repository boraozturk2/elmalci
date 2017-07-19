package com.bozturk.idle.dto;

public class StoreDto {

	private Long storeId;

	private String storeName;
	private Long countryId;
	private Long cityId;
	private Long countyId;
	private Long areaId;
	private Long neighborhoodId;
	private String cadde;
	private String sokak;
	private String kapi;
	private String apartman;
	private String daire;

	public StoreDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	@Override
	public String toString() {
		return "StoreDto [storeId=" + storeId + ", storeName=" + storeName + ", countryId=" + countryId + ", cityId="
				+ cityId + ", countyId=" + countyId + ", areaId=" + areaId + ", neighborhoodId=" + neighborhoodId
				+ ", cadde=" + cadde + ", sokak=" + sokak + ", kapi=" + kapi + ", apartman=" + apartman + ", daire="
				+ daire + "]";
	}


}
