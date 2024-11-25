package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepositiry extends CrudRepository<Consumer, Long>  {

	Consumer findOneByEmail(String email);
}
