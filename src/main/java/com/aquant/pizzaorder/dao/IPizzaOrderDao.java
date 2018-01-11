package com.aquant.pizzaorder.dao;

import java.util.Set;

import com.aquant.pizzaorder.model.PizzaOrderDto;

public interface IPizzaOrderDao {
	Set<PizzaOrderDto> getAllPizzaOrders();
	
	PizzaOrderDto orderPizza(String name);
}
