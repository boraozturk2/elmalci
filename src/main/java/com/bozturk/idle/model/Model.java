package com.bozturk.idle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "model")
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "model_id")
	private long modelId;

	@ManyToOne(targetEntity = Make.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "make_id")
	private long makeId;

	@Column(name = "name", length = 100)
	@NotEmpty(message = "*Model boş olamaz")
	private String name;

	public long getModelId() {
		return modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
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
