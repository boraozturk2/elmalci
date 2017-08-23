package com.bozturk.idle.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "user_listing")
public class UserListing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "listing_id")
	private long listingId;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@ManyToOne(targetEntity = UserStore.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "store_id")
	private UserStore store;

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;

	@Column(name = "count", length = 6)
	private Integer count;

	@Column(name = "header", length = 100)
	private String header;
	
	@Column(name = "description", length = 2000)
	private String description;
	
	@Digits(integer=8, fraction=2)
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "phone", length = 32)
	private String phone;
	
	@Column(name = "state", length = 2)
	private String state;

	@Column(name = "active")
	private Boolean active = true;
/*
	@OneToMany(mappedBy="userListing", fetch=FetchType.LAZY)
	private Set<UserListingPhoto> listingPhotos;
*/
	public UserListing() {
	}

	public long getListingId() {
		return listingId;
	}

	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserStore getStore() {
		return store;
	}

	public void setStore(UserStore store) {
		this.store = store;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
/*
	public Set<UserListingPhoto> getListingPhotos() {
		return listingPhotos;
	}

	public void setListingPhotos(Set<UserListingPhoto> listingPhotos) {
		this.listingPhotos = listingPhotos;
	}
*/
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserListing [listingId=" + listingId + ", user=" + user + ", store=" + store + ", product=" + product
				+ ", count=" + count + ", header=" + header + ", description=" + description + ", price=" + price
				+ ", phone=" + phone + ", state=" + state + ", active=" + active + "]";
	}

}