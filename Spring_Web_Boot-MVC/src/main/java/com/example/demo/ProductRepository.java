package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// поиск по названию
	List<Product> findByName(String name);
	// поиск по цене
	List<Product> findByPrise(Double prise);
	
	List<Product> findDistinctProductByConsumer_EmailContaining(String str);
}
