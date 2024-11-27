package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;

// Создать сущности: товары, категории, заказы.
// Подготовить серверную часть для "интернет-магазина"
// MVC+html будем вмесе в среду делать

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@SpringBootApplication
@RestController
public class SpringWebBootMvcApplication {
	
//	@Autowired
//	ShopService ShopServise;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebBootMvcApplication.class, args);
	}

}
