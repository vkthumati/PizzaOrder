package com.aquant.pizzaorder.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquant.pizzaorder.dao.IPizzaOrderDao;
import com.aquant.pizzaorder.model.PizzaOrderDto;
import com.aquant.pizzaorder.service.IPizzaOrderService;

@Service
public class PizzaOrderServiceImpl implements IPizzaOrderService{
	
	@Autowired
	private IPizzaOrderDao pizzaOrderDao;
	
	@Override
	public Set<PizzaOrderDto> getAllPizzaOrders() {
		return pizzaOrderDao.getAllPizzaOrders();
	}
	
	@Override
	public PizzaOrderDto orderPizza(String name) {
		return pizzaOrderDao.orderPizza(name);
	}
}
