package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;	
	
	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Override
	public Category findCategoryByName(String name) {
		
		return categoryRepository.findByName(name);
		//return null;
	}

	@Override
	public List<Product> findProductByName(String name) {
		
		return productRepository.findByName(name);
		//return null;
	}

	@Override
	public List<Product> findProductByPrise(Double prise) {
		
		return productRepository.findByPrise(prise);
		
		//return null;
	}

	@Override
	public Order FindOrderByOrderNumber(Long number) {
		
		return orderRepository.findByOrderNumber(number);
		//return null;
	}
	

}
