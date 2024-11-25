package com.example.demo;

import java.util.List;

public interface ShopService {
	
	List<Product> getProductByName(String name);
	List<Product> getProductByPrise(Double prise);
	List<Product> getAllProducts();
	
	List<Product> getProductByConsumerEmail(String email);
	
	void saveToDB(String name, Double prise, Consumer consumer);
	void saveToDB(Product product);
	
	
	void saveUserToDB(Consumer user);
	Consumer findByEmail(String email);
}
