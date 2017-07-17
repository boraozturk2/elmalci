package com.bozturk.idle.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

	private long id;
	private long parentId;
	private String name;
	private List<CategoryDto> categories = new ArrayList<>();

	public CategoryDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", parentId=" + parentId + ", name=" + name + ", categories=" + categories
				+ "]";
	}

	
}
