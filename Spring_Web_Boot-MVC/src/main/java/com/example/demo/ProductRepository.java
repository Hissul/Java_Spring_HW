package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	// поиск по названию
	List<Product> findByName(String name);
	// поиск по цене
	List<Product> findByPrise(Double prise);
}
