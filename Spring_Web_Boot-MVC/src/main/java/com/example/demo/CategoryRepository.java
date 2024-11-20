package com.example.demo;


import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	// поиск по названию категории
	Category findByName(String name);

}
