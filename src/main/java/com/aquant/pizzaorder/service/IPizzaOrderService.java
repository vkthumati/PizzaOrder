package com.aquant.pizzaorder.service;

import java.util.Set;

import com.aquant.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderService {
	
	Set<PizzaOrderDto> getAllPizzaOrders();
	
	PizzaOrderDto orderPizza(String name);

}
