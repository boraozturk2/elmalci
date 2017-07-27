package com.bozturk.idle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "make")
public class Make {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "make_id")
	private long makeId;

	@Column(name = "name", length = 100)
	@NotEmpty(message = "*Marka boş olamaz")
	private String name;

	public Make() {
	}

	public long getMakeId() {
		return makeId;
	}

	public void setMakeId(long makeId) {
		this.makeId = makeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
