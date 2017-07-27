package com.bozturk.idle.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bozturk.idle.model.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findById(long id);
	List<Category> findByActiveTrueOrderByLevelAscSortOrderAsc();
	Set<Category> findByLevelOrderByLevelAscSortOrderAsc(int level);
	Set<Category> findByParentIdOrderByLevelAscSortOrderAsc(long parentCategoryId);

}
