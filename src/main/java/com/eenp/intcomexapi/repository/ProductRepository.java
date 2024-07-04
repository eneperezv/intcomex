package com.eenp.intcomexapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.intcomexapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	List<Product> findAll();

}
