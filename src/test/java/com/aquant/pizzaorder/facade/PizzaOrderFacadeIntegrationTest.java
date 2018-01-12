package com.aquant.pizzaorder.facade;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aquant.pizzaorder.model.PizzaOrderDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaOrderFacadeIntegrationTest {
	@Autowired
	private IPizzaOrderFacade pizzaOrderFacade;
	
	@Test
	public void orderPizza() {
		PizzaOrderDto pizzaOrderDto = pizzaOrderFacade.orderPizza("testPizza");
		assertTrue(pizzaOrderDto!=null);
		assertTrue(pizzaOrderDto.getName()!=null);
		assertTrue(pizzaOrderDto.getName().equals("testPizza"));
	}

	@Test
	public void getAllOrders() {
		Set<PizzaOrderDto> set = pizzaOrderFacade.getAllPizzaOrders();
		assertTrue(set!=null);
		assertTrue(!set.isEmpty());
		assertTrue(set.size()>0);
	}
}
