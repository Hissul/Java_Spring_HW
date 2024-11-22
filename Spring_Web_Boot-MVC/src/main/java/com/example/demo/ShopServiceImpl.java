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


	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void saveToDB(String name, Double prise) {
		Product product = new Product(name, prise);
		productRepository.save(product);
	}

	@Override
	public void saveToDB(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return (List<Product>) productRepository.findAll();
	}

}
