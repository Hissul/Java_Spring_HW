package com.example.demo;



import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

	// поиск по номеру заказа
	Order findByOrderNumber(Long number); 
	
}
