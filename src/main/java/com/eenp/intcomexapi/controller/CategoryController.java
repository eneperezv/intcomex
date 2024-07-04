package com.eenp.intcomexapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.intcomexapi.entity.Category;
import com.eenp.intcomexapi.entity.ErrorDetails;
import com.eenp.intcomexapi.exception.CategoryNotFoundException;
import com.eenp.intcomexapi.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public ResponseEntity<?> getCategories(){
		List<Category> list = new ArrayList<Category>();
		try{
			categoryRepository.findAll().forEach(list::add);
			if(list.isEmpty()) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"NO CONTENT");
				return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable("categoryId") Long categoryId){
		Optional<Category> result = categoryRepository.findById(categoryId);
		if(result.isPresent()){
			return new ResponseEntity<Category>(result.get(), HttpStatus.OK);
		}else{
			//throw new CategoryNotFoundException("Category ID="+categoryId+" not found!");
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"ID #"+categoryId+" NOT FOUND");
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/category")
	public ResponseEntity<?> setCategory(@RequestBody Category category) {
		try{
			Category _category = categoryRepository.save(new Category(category.getCategoryName(),category.getDescription(),category.getPicture()));
			return new ResponseEntity<Category>(_category, HttpStatus.CREATED);
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
