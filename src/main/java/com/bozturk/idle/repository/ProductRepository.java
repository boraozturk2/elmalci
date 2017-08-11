package com.bozturk.idle.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.Product;

@Transactional
@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where category_id=:categoryId or side_category_id=:categoryId and active=true")
	Set<Product> findByCategoryIdOrSideCategoryId(@Param("categoryId") long categoryId);
	
	@Query("select distinct p.make from Product p where (category_id=:categoryId or side_category_id=:categoryId) and make like :makeP% and active=true")
	Set<Product> findByCategoryIdOrSideCategoryIdAndMake(@Param("categoryId") long categoryId, @Param("makeP") String makeP);
	
	Product findByCategoryIdAndMakeAndModelAndSerial(@Param("categoryId") long categoryId, @Param("make") String make, @Param("model") String model, @Param("serial") String serial);

}