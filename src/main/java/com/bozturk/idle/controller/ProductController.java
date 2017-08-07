package com.bozturk.idle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.IdDto;
import com.bozturk.idle.dto.ProductDto;
import com.bozturk.idle.model.Category;
import com.bozturk.idle.model.Product;
import com.bozturk.idle.repository.CategoryRepository;
import com.bozturk.idle.repository.ProductRepository;
import com.bozturk.idle.service.ProductService;

@Controller
public class ProductController extends MainController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam(required = false) Long productId,
			@RequestParam(required=false) Long cat1,@RequestParam(required=false) Long cat2,@RequestParam(required=false) Long cat3) {

		ModelAndView modelAndView = new ModelAndView();
		
		
		Product product = new Product();
		ProductDto productDto = new ProductDto();
		
		Set<Category> cat1s= categoryService.getCategoryData(1);
		productDto.setCat1s(cat1s);
		
		if (productId!=null){
			product = productRepository.findOne(productId);
			productDto.setProductId(product.getProductId());
			cat3 = product.getCategoryId();
			cat2 = categoryRepository.findById(cat3).getParentId();
			cat1 = categoryRepository.findById(cat2).getParentId();
			productDto.setCat1(cat1);
			productDto.setCat2(cat2);
			productDto.setCat3(cat3);
			productDto.setMake(product.getMake());
			productDto.setModel(product.getModel());
			productDto.setSerial(product.getSerial());
			productDto.setDescription(product.getDescription());
			productDto.setBarcode(product.getBarcode());
			
			if (product.getSideCategoryId()!=null) {
				Long cat3Side = product.getSideCategoryId();
				Long cat2Side = categoryRepository.findById(cat3Side).getParentId();
				Long cat1Side = categoryRepository.findById(cat2Side).getParentId();
				productDto.setCat1Side(cat1Side);
				productDto.setCat2Side(cat2Side);
				productDto.setCat3Side(cat3Side);
				modelAndView.addObject("cat1Side", cat1Side);
				modelAndView.addObject("cat2Side", cat2Side);
				modelAndView.addObject("cat3Side", cat3Side);
				
				Set<Category> cat2Sides = categoryService.getCategoryDataByParentCategory(cat1Side);
				Set<Category> cat3Sides = categoryService.getCategoryDataByParentCategory(cat2Side);
				productDto.setCat2Sides(cat2Sides);
				productDto.setCat3Sides(cat3Sides);
				modelAndView.addObject("cat2Sides", cat2Sides);
				modelAndView.addObject("cat3Sides", cat3Sides);
			}
			
		} 
		
		
		modelAndView.addObject("cat1s", cat1s);
		modelAndView.addObject("cat1Sides", cat1s);
		
		if (cat1!=null) {
			productDto.setCat1(cat1);
			Set<Category> cat2s = categoryService.getCategoryDataByParentCategory(cat1);
			productDto.setCat2s(cat2s);
			
			modelAndView.addObject("cat1",cat1);
			modelAndView.addObject("cat2s",cat2s);
		}
		if (cat2!=null) {
			productDto.setCat2(cat2);
			Set<Category> cat3s = categoryService.getCategoryDataByParentCategory(cat2);
			productDto.setCat3s(cat3s);
			modelAndView.addObject("cat2",cat2);
			modelAndView.addObject("cat3s",cat3s);
		}
		if (cat3!=null) {
			productDto.setCat3(cat3);
			modelAndView.addObject("cat3",cat3);
		}
		
		modelAndView.addObject("productDto", productDto);
		modelAndView.setViewName("/user/product");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product/category", method = RequestMethod.GET)
	public @ResponseBody Set<Product> getProduct(@RequestParam Long categoryId) {

		return productRepository.findByCategoryIdOrSideCategoryId(categoryId);
	}
	

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product/category/make", method = RequestMethod.POST)
	public @ResponseBody List<Product> getProductMake(@RequestParam Long categoryId, @RequestParam String make) {
		return productService.findDistinctProductsByCategoryMake(categoryId, make);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product/category/make/model", method = RequestMethod.POST)
	public @ResponseBody List<Product> getProductModel(@RequestParam Long categoryId, @RequestParam String make, @RequestParam String model) {
		return productService.findDistinctProductsByCategoryMakeModel(categoryId, make, model);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product/category/make/model/serial", method = RequestMethod.POST)
	public @ResponseBody List<Product> getProductSerial(@RequestParam Long categoryId, @RequestParam String make, @RequestParam String model, @RequestParam String serial) {
		return productService.findDistinctProductsByCategoryMakeModelSerial(categoryId, make, model, serial);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/products", method = RequestMethod.GET)
	public ModelAndView getProducts() {


		ModelAndView modelAndView = new ModelAndView();
		
		Set<Category> cat1s= categoryService.getCategoryData(1);
		modelAndView.addObject("cat1s", cat1s);
		modelAndView.addObject("idDto", new IdDto());
		modelAndView.addObject("products", new ArrayList<Product>());
		modelAndView.addObject("categoryId", 0L);
		modelAndView.setViewName("/user/products");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/product", method = RequestMethod.POST)
	public ModelAndView getProducts(@Valid ProductDto productDto, BindingResult result, ModelAndView modelAndView) {

		Product product = null;
		if (productDto.getProductId()!=null && productDto.getProductId()!=0) {
			product = productRepository.findOne(productDto.getProductId());
		} else {
			product = new Product(); 
		}

		product.setCategoryId(productDto.getCat3());
		product.setCategoryName(categoryRepository.findById(productDto.getCat3()).getName());
		if (productDto.getCat3Side()!=null) {
			product.setSideCategoryId(productDto.getCat3Side());
			product.setSideCategoryName(categoryRepository.findById(productDto.getCat3Side()).getName());
		} else {
			product.setSideCategoryId(null);
			product.setSideCategoryName(null);
		}
		
		product.setMake(productDto.getMake());
		product.setModel(productDto.getModel());
		product.setSerial(productDto.getSerial());
		product.setDescription(productDto.getDescription());
		product.setBarcode(productDto.getBarcode());
		productRepository.save(product);
		
		//Set<Category> cat1s= categoryService.getCategoryData(1);
		//modelAndView.addObject("cat1s", cat1s);
		modelAndView = getProducts();
		modelAndView.addObject("cat1", productDto.getCat1());
		modelAndView.addObject("cat2s", categoryService.getCategoryDataByParentCategory(productDto.getCat1()));
		modelAndView.addObject("cat2", productDto.getCat2());
		modelAndView.addObject("cat3s", categoryService.getCategoryDataByParentCategory(productDto.getCat2()));
		modelAndView.addObject("cat3", productDto.getCat3());
		modelAndView.addObject("products", productRepository.findByCategoryIdOrSideCategoryId(productDto.getCat3()));
		return modelAndView;
		
	}
	
	

	
}
