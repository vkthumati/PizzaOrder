package com.aquant.pizzaorder.facade.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquant.pizzaorder.facade.IPizzaOrderFacade;
import com.aquant.pizzaorder.model.PizzaOrderDto;
import com.aquant.pizzaorder.service.IPizzaOrderService;

@Service
public class PizzaOrderFacadeImpl implements IPizzaOrderFacade{
	
	@Autowired
	private IPizzaOrderService pizzaOrderService;
	
	public Set<PizzaOrderDto> getAllPizzaOrders(){
		return pizzaOrderService.getAllPizzaOrders();
	}
	
	@Override
	public PizzaOrderDto orderPizza(String name) {
		return pizzaOrderService.orderPizza(name);
	}
}
