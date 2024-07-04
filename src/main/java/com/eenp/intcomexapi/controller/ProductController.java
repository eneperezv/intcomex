package com.eenp.intcomexapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eenp.intcomexapi.entity.ErrorDetails;
import com.eenp.intcomexapi.entity.Product;
import com.eenp.intcomexapi.repository.ProductRepository;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	public Page<Product> getAllProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }
	
	@GetMapping("/products")
	public ResponseEntity<?> getProducts(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize){
		Page<Product> products = (Page<Product>) getAllProducts(pageNumber,pageSize);
		if(!products.isEmpty()){
			return ResponseEntity.ok(products);
		}else{
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"NO CONTENT FOUND");
			return new ResponseEntity<>(err,HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") long productId){
		Optional<Product> result = productRepository.findById(productId);
		if(result.isPresent()){
			return new ResponseEntity<Product>(result.get(), HttpStatus.OK);
		}else{
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NO_CONTENT.toString(),"ID #"+productId+" NOT FOUND");
			return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> setProduct() {
		try {
			Faker faker;
			Product product;
			List<Product> listToSave = new ArrayList<Product>();
			long startTime = System.currentTimeMillis();
			System.out.println("generating data...");
			for(int i = 1; i<=100000; i++) {
				faker = new Faker();
				product = new Product(
							faker.app().name(),
							faker.number().numberBetween(1,4),
							faker.number().numberBetween(1,3),
							faker.number().numberBetween(1,50),
							faker.number().randomDouble(2,1,350),
							faker.number().numberBetween(1,50),
							faker.number().numberBetween(1,150),
							1,
							faker.bool().bool()
						);
				listToSave.add(product);
			}
			System.out.println("saving data...");
			productRepository.saveAll(listToSave);
			long endTime = (System.currentTimeMillis() - startTime) / 1000;
			System.out.println("finished..."+endTime);
			System.out.println("elapsed time: "+endTime+" seconds");
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		}catch(Exception e) {
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),"INTERNAL SERVER ERROR");
			return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
