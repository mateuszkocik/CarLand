package com.carland.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.carland.entity.User;
import com.carland.service.UserService;
import com.carland.user.CrmUser;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneralTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;
	
	
	@Test
	public void homePageTest() throws Exception{
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void showLoginTest() throws Exception{
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
	
	@Test
	public void unauthenticatedUserOpenSellmycar() throws Exception{
		this.mockMvc.perform(get("/sellmycar")).andExpect(status().isMovedTemporarily());
	}
	
	
	@Test
	public void findByUsernameTest() throws Exception{
		User afterSave = userService.findByUsername("mateusz4577@o2.pl");
		
		assertThat(afterSave.getName()).isEqualTo("Mateusz");
	}
	
	@Test
	public void saveTest() throws Exception{
		CrmUser user = new CrmUser();
		user.setUsername("user@gmail.com");
		user.setPassword("test123");
		user.setCity("A");
		user.setName("John");
		user.setPostalCode("12-123");
		user.setStreet("B");
		user.setTelephoneNumber("123456789");
		
		userService.saveUser(user);
		
		User afterSave = userService.findByUsername("user@gmail.com");
		
		assertThat(afterSave.getName()).isEqualTo("John");
	}
	
	
	
	
	
	
}
