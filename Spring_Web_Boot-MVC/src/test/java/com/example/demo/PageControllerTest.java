package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PageControllerTest {
	
	 @Autowired
	 private ConsumerRepositiry consumerRepository;  // Репозиторий, который мы мокируем

    @Autowired
    private MockMvc mockMvc;  // Для работы с MockMvc
    
    @Mock
    private ConsumerService consumerService;
    @Mock
    private ShopService shopService;
	
	
	@Test
	void shouldReturnDefaultMessage() throws Exception {
		mockMvc.perform(get("/test_url")
				   ).andDo(print()
				   ).andExpect(status().isOk()
				   ).andExpect(content().string(containsString("Test Response String")));
	}
	

	//@GetMapping("/registration")
	@Test
	public void redirectToRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(view().name("registration")); // Проверка, что возвращаемое имя представления "registration"
	}
	
	
	//@PostMapping("/postRegistr")
	@Test
	void createNewCustomer() throws Exception {
	  this.mockMvc
	    .perform(post("/postRegistr")
	      .param("firstName", "Duke")
	      .param("lastName", "Nukem")
	      .param("password", "123")
	      .param("email", "s@s.ru")
	    ).andExpect(status().isOk());	    
	 
	  assertThat(consumerRepository.findOneByEmail("s@s.ru")).isNotNull();	  
	}
	
	
	//@GetMapping("/addPurchase")
	@Test
	@WithMockUser(username = "testuser", roles = {"ADMIN"})
    public void testAddPurchaseWithAuthorizedUser() throws Exception {
        mockMvc.perform(get("/addPurchase"))
                .andExpect(status().isOk()) // проверяем, что статус 200 OK
                .andExpect(view().name("addPurchase")); // проверяем, что возвращается представление "addPurchase"
    }
	


	//@PostMapping("/addOnePurchase")
	@Test
	void addOnePurchase() throws Exception {
		
		this.mockMvc.perform(post("/addOnePurchase").with(user("admin").password("pass").roles("USER","ADMIN"))
				.param("name", "bbbb")
				.param("prise", String.valueOf(123.123)))
		.andExpect(status().isOk());
	}
	
	
	//@GetMapping("/login")
	@Test
	public void redirectToLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login")); // Проверка, что возвращаемое имя представления "registration"
	}
	
	
	//@GetMapping("/allPurchases")
	// тест не проходит , т.к. consumerService.consumer is null
	// не разобрался как его правильно мокать 	
	@Test
    @WithMockUser(username = "consumer@example.com", roles = {"USER"})
    public void testAllPurchases() throws Exception {
		
        when(consumerService.consumer.getEmail()).thenReturn("consumer@example.com");
        when(shopService.getProductByConsumerEmail("consumer@example.com"))
            .thenReturn(Collections.singletonList(new Product("Product 1", 100.12, consumerService.consumer)));

        mockMvc.perform(get("/allPurchases"))
            .andExpect(status().isOk())
            .andExpect(view().name("allPurchases"))
            .andExpect(model().attribute("my_param", "absent"))
            .andExpect(model().attributeExists("products"));
    }

}
