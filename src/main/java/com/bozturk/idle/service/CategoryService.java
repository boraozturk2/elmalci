package com.bozturk.idle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozturk.idle.dto.CategoryDto;
import com.bozturk.idle.model.Category;
import com.bozturk.idle.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDto getCategoryData() {

		CategoryDto categoryDto = new CategoryDto();
		List<Category> cats = categoryRepository.findByActiveTrueOrderByLevelAscSortOrderAsc();

		for (Category cat : cats) {

			CategoryDto addMe = new CategoryDto();
			addMe.setId(cat.getId());
			addMe.setParentId(cat.getParentId());
			addMe.setName(cat.getName());

			if (cat.getLevel() == 1) {
				categoryDto.getCategories().add(addMe);
			}
			if (cat.getLevel() == 2) {
				for (CategoryDto catDto : categoryDto.getCategories()) {
					if (catDto.getId() == cat.getParentId()) {
						catDto.getCategories().add(addMe);
						break;
					}
				}
			}
			if (cat.getLevel() == 3) {
				for (CategoryDto catDto : categoryDto.getCategories()) {
					for (CategoryDto catDtoSub : catDto.getCategories()) {
						if (catDtoSub.getId() == cat.getParentId()) {
							catDtoSub.getCategories().add(addMe);
							break;
						}
					}
				}
			}
		}
		return categoryDto;
	}

}
