package com.bozturk.idle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "email", length=100)
	@Email(message = "*Lütfen düzgün bir email giriniz")
	@NotEmpty(message = "*Email boş olamaz")
	private String email;
	
	@Column(name = "password", length=100)
	@Length(min = 6, max=100, message = "*Şifre 6-16 karakter arası olmalıdır.")
	@NotEmpty(message = "*Şifre boş olamaz")
	@Transient
	private String password;
	
	@NotEmpty(message = "*Şifre tekrarı boş olamaz")
	@Transient
	@javax.persistence.Transient
	private String repassword;
	
	@Column(name = "name", length=70)
	@NotEmpty(message = "*İsim boş olamaz")
	private String name;
	
	@Column(name = "last_name", length=100)
	@NotEmpty(message = "*Soyisim ")
	private String lastName;
	
	@Column(name = "phone", length=32)
	@NotEmpty(message = "*Telefon boş olamaz")
	private String phone;
	
	@Column(name = "active")
	private boolean active;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
