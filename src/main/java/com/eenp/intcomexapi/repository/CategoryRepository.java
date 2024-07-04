package com.eenp.intcomexapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eenp.intcomexapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	List<Category> findAll();

}
