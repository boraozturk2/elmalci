package com.bozturk.idle.dto;

import java.util.HashSet;
import java.util.Set;

import com.bozturk.idle.model.Category;

public class ProductDto {

	private Long productId;

	private Long cat1;
	private Long cat1Side;
	private Set<Category> cat1s = new HashSet<>();
	private Set<Category> cat1Sides = new HashSet<>();

	private Long cat2;
	private Long cat2Side;
	private Set<Category> cat2s = new HashSet<>();
	private Set<Category> cat2Sides = new HashSet<>();

	private Long cat3;
	private Long cat3Side;
	private Set<Category> cat3s = new HashSet<>();
	private Set<Category> cat3Sides = new HashSet<>();

	private String make;
	private String model;
	private String description;
	private String barcode;

	public ProductDto() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCat3() {
		return cat3;
	}

	public void setCat3(Long cat3) {
		this.cat3 = cat3;
	}

	public Long getCat3Side() {
		return cat3Side;
	}

	public void setCat3Side(Long cat3Side) {
		this.cat3Side = cat3Side;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Long getCat1() {
		return cat1;
	}

	public void setCat1(Long cat1) {
		this.cat1 = cat1;
	}

	public Long getCat1Side() {
		return cat1Side;
	}

	public void setCat1Side(Long cat1Side) {
		this.cat1Side = cat1Side;
	}

	public Long getCat2() {
		return cat2;
	}

	public void setCat2(Long cat2) {
		this.cat2 = cat2;
	}

	public Long getCat2Side() {
		return cat2Side;
	}

	public void setCat2Side(Long cat2Side) {
		this.cat2Side = cat2Side;
	}

	public Set<Category> getCat1s() {
		return cat1s;
	}

	public void setCat1s(Set<Category> cat1s) {
		this.cat1s = cat1s;
	}

	public Set<Category> getCat2s() {
		return cat2s;
	}

	public void setCat2s(Set<Category> cat2s) {
		this.cat2s = cat2s;
	}

	public Set<Category> getCat3s() {
		return cat3s;
	}

	public void setCat3s(Set<Category> cat3s) {
		this.cat3s = cat3s;
	}

	public Set<Category> getCat1Sides() {
		return cat1Sides;
	}

	public void setCat1Sides(Set<Category> cat1Sides) {
		this.cat1Sides = cat1Sides;
	}

	public Set<Category> getCat2Sides() {
		return cat2Sides;
	}

	public void setCat2Sides(Set<Category> cat2Sides) {
		this.cat2Sides = cat2Sides;
	}

	public Set<Category> getCat3Sides() {
		return cat3Sides;
	}

	public void setCat3Sides(Set<Category> cat3Sides) {
		this.cat3Sides = cat3Sides;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", cat1=" + cat1 + ", cat1Side=" + cat1Side + ", cat1s=" + cat1s
				+ ", cat1Sides=" + cat1Sides + ", cat2=" + cat2 + ", cat2Side=" + cat2Side + ", cat2s=" + cat2s
				+ ", cat2Sides=" + cat2Sides + ", cat3=" + cat3 + ", cat3Side=" + cat3Side + ", cat3s=" + cat3s
				+ ", cat3Sides=" + cat3Sides + ", make=" + make + ", model=" + model + ", description=" + description
				+ ", barcode=" + barcode + "]";
	}

}
