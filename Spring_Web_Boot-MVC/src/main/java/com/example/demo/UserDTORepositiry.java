package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserDTORepositiry extends CrudRepository<UserDTO, Long>  {

	UserDTO findOneByEmail(String email);
}
