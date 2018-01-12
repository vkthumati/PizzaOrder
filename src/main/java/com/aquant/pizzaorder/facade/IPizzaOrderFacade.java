package com.aquant.pizzaorder.facade;

import java.util.Set;

import com.aquant.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderFacade {
	Set<PizzaOrderDto> getAllPizzaOrders();
	PizzaOrderDto orderPizza(String name);
	void loadPizzaOrdersData();
}
