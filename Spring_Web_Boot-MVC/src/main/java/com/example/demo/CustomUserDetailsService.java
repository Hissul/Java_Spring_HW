package com.example.demo;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	    private final ConsumerService consumerService; // Assume this interacts with your DB
	    private final PasswordEncoder passwordEncoder;

	    public CustomUserDetailsService(ConsumerService consumerService, PasswordEncoder passwordEncoder) {
	        this.consumerService = consumerService;
	        this.passwordEncoder = passwordEncoder;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Fetch user from DB
	    		Optional<Consumer> user = Optional.ofNullable(consumerService.findByEmail(username));
	       
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with email: " + username);
	        }

	        // Create UserDetails object
	        return User.builder()
	                   .username(user.get().getEmail()) // Assume 'email' is the username
	                   .password(user.get().getPassword()) // Ensure passwords are stored encrypted
	                   .build();
	    }
}
