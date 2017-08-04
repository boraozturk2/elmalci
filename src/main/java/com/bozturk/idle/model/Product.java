package com.bozturk.idle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long productId;

	@Column(name = "make", length = 100)
	@NotEmpty(message = "*Marka boş olamaz")
	private String make;

	@Column(name = "model", length = 100)
	@NotEmpty(message = "*Model boş olamaz")
	private String model;

	@Column(name = "serial", length = 100)
	@NotEmpty(message = "*SeriNo boş olamaz")
	private String serial;

	@Column(name = "barcode", length = 100)
	private String barcode;

	@Column(name = "category_id", length = 36)
	private Long categoryId;

	@Column(name = "category_name", length = 100)
	private String categoryName;

	@Column(name = "side_category_id", length = 36)
	private Long sideCategoryId;

	@Column(name = "side_category_name", length = 100)
	private String sideCategoryName;

	@Column(name = "description", length = 200)
	private String description;

	@Column(name = "creator", length = 50)
	private String creator;

	@Column(name = "status", length = 2)
	private String status;

	@Column(name = "active")
	private boolean active = true;

	public Product() {
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSideCategoryId() {
		return sideCategoryId;
	}

	public void setSideCategoryId(Long sideCategoryId) {
		this.sideCategoryId = sideCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSideCategoryName() {
		return sideCategoryName;
	}

	public void setSideCategoryName(String sideCategoryName) {
		this.sideCategoryName = sideCategoryName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}
