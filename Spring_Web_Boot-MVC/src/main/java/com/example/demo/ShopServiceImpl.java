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
	private ConsumerRepositiry userDTORepositiry;
	
	
	@Override
	public List<Product> getProductByName(String name) {
		
		return productRepository.findByName(name);
		//return null;
	}

	@Override
	public List<Product> getProductByPrise(Double prise) {
		
		return productRepository.findByPrise(prise);
		
		//return null;
	}


	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void saveToDB(String name, Double prise, Consumer consumer) {
		Product product = new Product(name, prise, consumer);
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

	@Override
	public void saveUserToDB(Consumer user) {
		userDTORepositiry.save(user);		
	}

	@Override
	public Consumer findByEmail(String email) {		
		return userDTORepositiry.findOneByEmail(email);
	}

	@Override
	public List<Product> getProductByConsumerEmail(String email) {
		
		return productRepository.findDistinctProductByConsumer_EmailContaining(email);
	}

}
