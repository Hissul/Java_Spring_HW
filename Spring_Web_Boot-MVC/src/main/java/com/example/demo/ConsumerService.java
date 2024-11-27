package com.example.demo;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ConsumerService {
	
	@Autowired
	private ConsumerRepositiry repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Consumer addUser(Consumer user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public CompletableFuture<Consumer> addUserAsync(Consumer user)
	{
		return CompletableFuture.supplyAsync(() -> addUser(user));
	}
	
	public Consumer findUser(Long id)
	{
		Optional<Consumer> user = repo.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		else
		{
			return null;
		}
	}
	
	public Consumer findByEmail(String email)
	{
		return repo.findOneByEmail(email);
	}
	
	public CompletableFuture<Consumer> findByEmailAsync(String email)
	{
		return CompletableFuture.supplyAsync(() -> findByEmail(email));
	}

}
