package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest

@ExtendWith(MockitoExtension.class)
public class PageControllerTest {
	
	 @Autowired
	 private ConsumerRepositiry consumerRepository;  // Репозиторий, который мы мокируем

    @Autowired
    private MockMvc mockMvc;  // Для работы с MockMvc
	
	
	@Test
	void shouldReturnDefaultMessage() throws Exception {
		mockMvc.perform(get("/test_url")
				   ).andDo(print()
				   ).andExpect(status().isOk()
				   ).andExpect(content().string(containsString("Test Response String")));
	}
	
	
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
	


	@Test
	void addOnePurchase() throws Exception {
		
		this.mockMvc.perform(post("/addOnePurchase").with(user("admin").password("pass").roles("USER","ADMIN"))
				.param("name", "bbbb")
				.param("prise", String.valueOf(123.123)))
		.andExpect(status().isOk());
	}
	
	
	@WithMockUser(username = "qq.@qq.ru", roles = {"USER"})
	@Test
	void redirectToAddPurchase() throws Exception {
		
		this.mockMvc.perform(get("/addOnePurchase").with(user("admin").password("pass").roles("USER","ADMIN"))
				).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/addPurchase"));
		
//	    // Мокаем репозиторий пользователя
//	    when(consumerRepository.findOneByEmail("qq.@qq.ru"))
//	            .thenReturn(Optional.of(new Consumer("John", "Doe", "qq.@qq.ru", "123")));
//
//	    // Выполняем GET-запрос к /addPurchase
//	    this.mockMvc.perform(get("/addPurchase"))
//	            .andDo(print())
//	            .andExpect(status().is3xxRedirection())  // Ожидаем редирект (3xx статус)
//	            .andExpect(redirectedUrl("/addPurchase"));  // Проверяем правильный URL для редиректа
	}

}
