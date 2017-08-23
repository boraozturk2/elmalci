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

	@Column(name = "listing_id", length = 36)
	private Long listingId;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content")
	private String content;

	@Column(name = "file_type", length = 32)
	private String fileType;
	
	@Column(name = "porder", length = 2)
	private Integer porder;

	public UserListingPhoto() {
		// TODO Auto-generated constructor stub
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public Long getListingId() {
		return listingId;
	}

	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getPorder() {
		return porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
	}

	
	

}