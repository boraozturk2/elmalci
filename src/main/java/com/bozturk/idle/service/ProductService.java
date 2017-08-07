package com.bozturk.idle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozturk.idle.dao.ProductDao;
import com.bozturk.idle.model.Product;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	
	public List<Product> findDistinctProductsByCategoryMake(Long categoryId, String makeLike) {
		return productDao.findDistinctProductsByCategoryMake(categoryId, makeLike);
	}
	
	public List<Product> findDistinctProductsByCategoryMakeModel(Long categoryId, String make, String modelLike) {
		return productDao.findDistinctProductsByCategoryModel(categoryId, make, modelLike);
	}
	
	public List<Product> findDistinctProductsByCategoryMakeModelSerial(Long categoryId, String make, String model, String serialLike) {
		return productDao.findDistinctProductsByCategorySerial(categoryId, make, model, serialLike);
	}
}
