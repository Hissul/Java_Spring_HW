package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PageControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ConsumerRepositiry consumerRepositiry;
	
	
	@Test
	void createNewCustomer() throws Exception {
	  this.mockMvc
	    .perform(post("/postRegistr")
	      .param("firstName", "Duke")
	      .param("lastName", "Nukem")
	      .param("password", "123")
	      .param("email", "s@s.ru")
	    ).andExpect(status().isOk());	    
	 
	  assertThat(consumerRepositiry.findOneByEmail("s@s.ru")).isNotNull();
	  
	}
	
	@Test
	void shouldReturnDefaultMessage() throws Exception {
		mockMvc.perform(get("/test_url")
				   ).andDo(print()
				   ).andExpect(status().isOk()
				   ).andExpect(content().string(containsString("Test Response String")));
	}


}
