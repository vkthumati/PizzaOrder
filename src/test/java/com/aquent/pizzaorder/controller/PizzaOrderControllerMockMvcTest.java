package com.aquent.pizzaorder.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaOrderControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void orderPizza() throws Exception {
	    this.mockMvc.perform(post("/api/v1/pizzaOrder").content("testPizza").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).
	    andDo(print()).andExpect(status().isCreated())
        .andExpect(content().string(containsString("testPizza")));
	}
	
	@Test
	public void getAllPizzaOrders() throws Exception {
	    this.mockMvc.perform(get("/api/v1/allOrders")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("meat")));
	}
	
}
