package com.aquent.pizzaorder.dao;

import java.util.Set;

import com.aquent.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderDao {
	Set<PizzaOrderDto> getAllPizzaOrders();
	PizzaOrderDto orderPizza(String name);
	void loadPizzaOrdersData();
}
