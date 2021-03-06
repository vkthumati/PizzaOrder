package com.aquent.pizzaorder.facade;

import java.util.Set;

import com.aquent.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderFacade {
	Set<PizzaOrderDto> getAllPizzaOrders();
	PizzaOrderDto orderPizza(String name);
	void loadPizzaOrdersData();
}
