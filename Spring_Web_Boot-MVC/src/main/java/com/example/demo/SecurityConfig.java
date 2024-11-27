package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig {
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                               .username("user")
                               .password(encoder.encode("password"))
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            	.requestMatchers("/registration", "/login", "/postRegistr", "/static/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")           // Custom login page
                    .loginProcessingUrl("/login")  // URL to submit the login form
                    .usernameParameter("email")    // Parameter name for username
                    .passwordParameter("password") // Parameter name for password
                    .defaultSuccessUrl("/main", true)  // Redirect after successful login
                    .failureUrl("/login?error=true") // Redirect after failed login
                )
            .logout(Customizer.withDefaults())
        	.userDetailsService(customUserDetailsService);
        return http.build();
    }
}
