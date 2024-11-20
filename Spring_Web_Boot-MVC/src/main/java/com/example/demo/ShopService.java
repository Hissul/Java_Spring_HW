package com.example.demo;

import java.util.List;

public interface ShopService {
	
	Category findCategoryByName(String name);
	
	List<Product> findProductByName(String name);
	List<Product> findProductByPrise(Double prise);
	
	Order FindOrderByOrderNumber(Long number);
	
}
