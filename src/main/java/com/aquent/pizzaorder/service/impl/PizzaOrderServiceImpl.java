package com.aquent.pizzaorder.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquent.pizzaorder.dao.IPizzaOrderDao;
import com.aquent.pizzaorder.model.PizzaOrderDto;
import com.aquent.pizzaorder.service.IPizzaOrderService;

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
	
	@Override
	public void loadPizzaOrdersData() {
		pizzaOrderDao.loadPizzaOrdersData();
	}
	
	/*public static void main(String[] args) {
		System.getProperties().keySet().stream().forEach(e->System.out.println(e+":"+System.getProperty(e.toString())));
	}*/
}
