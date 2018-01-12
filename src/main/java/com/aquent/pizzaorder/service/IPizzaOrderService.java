package com.aquent.pizzaorder.service;

import java.util.Set;

import com.aquent.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderService {
	Set<PizzaOrderDto> getAllPizzaOrders();
	PizzaOrderDto orderPizza(String name);
	void loadPizzaOrdersData();
}
