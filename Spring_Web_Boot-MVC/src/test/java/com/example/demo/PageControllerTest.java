package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	void addOnePurchase() throws Exception {
		//Consumer consumer = new Consumer("W", "W", "123", "d@d.ru");
		this.mockMvc.perform(post("/addOnePurchase").with(user("admin").password("pass").roles("USER","ADMIN"))
				.param("name", "bbbb")
				.param("prise", String.valueOf(123.123)))
		.andExpect(status().isOk());
	}

}
