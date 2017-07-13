package com.bozturk.idle.dto;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class UserPasswordDTO implements Serializable {

	private static final long serialVersionUID = -2221852531645649922L;

	@Length(min = 8)
	private String password;

	@Length(min = 8)
	private String repassword;

	private String verificationToken;
	private long userId;

	// region Constructors

	public UserPasswordDTO() {
	}

	public UserPasswordDTO(long userId, String verificationToken) {
		this.verificationToken = verificationToken;
		this.userId = userId;
	}

	public UserPasswordDTO(long userId, String verificationToken, String password, String repassword) {
		this.password = password;
		this.repassword = repassword;
		this.verificationToken = verificationToken;
		this.userId = userId;
	}

	// endregion

	// region Getter/Setters

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	// endregion

}