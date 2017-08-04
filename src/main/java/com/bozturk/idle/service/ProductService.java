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
	
	
	public List<Product> findDistinctProductsByCategory(Long categoryId, String makeLike) {
		
		return productDao.findDistinctProductsByCategory(categoryId, makeLike);
		
	}
}
