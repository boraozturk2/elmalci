package com.bozturk.idle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.dao.ProductDao;
import com.bozturk.idle.model.Product;
import com.bozturk.idle.repository.ProductRepository;

@Transactional
@Service("productService")
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> findDistinctProductsByCategoryMake(Long categoryId, String makeLike) {
		return productDao.findDistinctProductsByCategoryMake(categoryId, makeLike);
	}
	
	public List<Product> findDistinctProductsByCategoryMakeModel(Long categoryId, String make, String modelLike) {
		return productDao.findDistinctProductsByCategoryModel(categoryId, make, modelLike);
	}
	
	public List<Product> findDistinctProductsByCategoryMakeModelSerial(Long categoryId, String make, String model, String serialLike) {
		return productDao.findDistinctProductsByCategorySerial(categoryId, make, model, serialLike);
	}
	
	public Product findDistinctProduct(Long categoryId, String make, String model, String serial) {
		return productRepository.findByCategoryIdAndMakeAndModelAndSerial(categoryId, make, model, serial);
	}
}
