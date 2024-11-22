package com.example.demo;

import java.util.List;

public interface ShopService {
	
	List<Product> findProductByName(String name);
	List<Product> findProductByPrise(Double prise);
	void saveToDB(String name, Double prise);
	void saveToDB(Product product);
	List<Product> getAllProducts();
	
	void saveUserToDB(UserDTO user);
}
