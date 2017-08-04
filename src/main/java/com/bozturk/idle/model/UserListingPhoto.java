package com.bozturk.idle.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_listing_photo")
public class UserListingPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "photo_id")
	private long photoId;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "listing_id")
	private UserListing userListing;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content")
	private byte[] content;

	@Column(name = "file_type", length = 32)
	private String fileType;

	public UserListingPhoto() {
		// TODO Auto-generated constructor stub
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public UserListing getUserListing() {
		return userListing;
	}

	public void setUserListing(UserListing userListing) {
		this.userListing = userListing;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}