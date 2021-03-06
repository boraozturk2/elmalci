package com.bozturk.idle.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.dto.CategoryDto;
import com.bozturk.idle.model.Category;
import com.bozturk.idle.repository.CategoryRepository;

@Transactional
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
	
	public Set<Category> getCategoryData(int level) {

		return categoryRepository.findByLevelOrderByLevelAscSortOrderAsc(level);
	}
	
	public Set<Category> getCategoryDataByParentCategory(long parentId) {

		return categoryRepository.findByParentIdOrderByLevelAscSortOrderAsc(parentId);
	}

}
