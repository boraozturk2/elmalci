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
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.IdDto;
import com.bozturk.idle.dto.ListingDto;
import com.bozturk.idle.dto.ProductDto;
import com.bozturk.idle.model.Category;
import com.bozturk.idle.model.Product;
import com.bozturk.idle.model.UserListing;
import com.bozturk.idle.repository.CategoryRepository;
import com.bozturk.idle.repository.ProductRepository;
import com.bozturk.idle.repository.UserListingRepository;
import com.bozturk.idle.service.StoreService;

@Controller
public class ListingController extends MainController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserListingRepository listingRepository;
	
	@Autowired
	private StoreService storeService;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/listing", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam(required = false) Long productId,
			@RequestParam(required=false) Long cat1,@RequestParam(required=false) Long cat2,@RequestParam(required=false) Long cat3) {

		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("stores", storeService.getUserStores());
		
		Product product = new Product();
		ListingDto productDto = new ListingDto();
		
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
		
		modelAndView.addObject("listingDto", productDto);
		modelAndView.setViewName("/user/listing");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/listings", method = RequestMethod.GET)
	public ModelAndView getProducts() {

		ModelAndView modelAndView = new ModelAndView();
		
		List<UserListing> cat1s= listingRepository.findAll();
		modelAndView.addObject("cat1s", cat1s);
		modelAndView.addObject("idDto", new IdDto());
		modelAndView.addObject("products", new ArrayList<Product>());
		modelAndView.addObject("categoryId", 0L);
		modelAndView.setViewName("/user/listings");
		addMissingObjects(modelAndView);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user/listing", method = RequestMethod.POST)
	public ModelAndView getProducts(@Valid ProductDto productDto, BindingResult result, ModelAndView modelAndView) {

		return modelAndView;
		
	}
	
	

	
}
