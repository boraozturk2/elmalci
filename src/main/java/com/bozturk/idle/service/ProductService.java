package com.bozturk.idle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bozturk.idle.model.Product;
import com.bozturk.idle.repository.ProductRepository;

@Service("productService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductsData(Long categoryId) {

		return new ArrayList<Product>();
	}

}
