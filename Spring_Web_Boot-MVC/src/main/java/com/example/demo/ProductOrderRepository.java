package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

	// поиск по продукту	
	// поиск по номеру заказа
}
