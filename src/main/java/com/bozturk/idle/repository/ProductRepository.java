package com.bozturk.idle.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bozturk.idle.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where category_id=:categoryId or side_category_id=:categoryId and active=true")
	Set<Product> findByCategoryIdOrSideCategoryId(@Param("categoryId") long categoryId);

}