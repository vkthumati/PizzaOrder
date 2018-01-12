package com.aquant.pizzaorder.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.aquant.pizzaorder.dao.IPizzaOrderDao;
import com.aquant.pizzaorder.model.PizzaOrderDto;

@Repository
public class PizzaOrderDaoImpl implements IPizzaOrderDao {
	@Value("${source.path}")
	private String soursePath;
	
	@Value("${destination.path}")
	private String destinationPath;
	
	@Override
	public void loadPizzaOrdersData() {
		File file = new File(destinationPath);
		try (	
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out =  new PrintWriter(bw);
				Stream<String> lines = Files.lines(Paths.get(soursePath))
			) {
			Set<PizzaOrderDto> pizzaOrders = lines.map(line -> {
				  String orderDetails[] = line.split(",");
				  return new PizzaOrderDto(orderDetails[0], Long.valueOf(orderDetails[1]));
			  }).collect(Collectors.toCollection(TreeSet::new));
			
			pizzaOrders.stream().forEachOrdered(pizzaOrderDto ->{
				out.println(pizzaOrderDto.getName()+","+pizzaOrderDto.getTime());
			    out.flush();
			});
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public Set<PizzaOrderDto> getAllPizzaOrders() {
		//Files.lines(Paths.get(getClass().getClassLoader().getResource("pizzaorders.txt").toURI()), StandardCharsets.UTF_8)
		File file = new File(destinationPath);
		Set<PizzaOrderDto> pizzaOrders=null;
		try (
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out =  new PrintWriter(bw);
				Stream<String> lines = Files.lines(Paths.get(destinationPath))
			) {
				pizzaOrders = lines.map(line -> {
				  String orderDetails[] = line.split(",");
				  return new PizzaOrderDto(orderDetails[0], Long.valueOf(orderDetails[1]));
			  }).collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return pizzaOrders;
	}
	
	@Override
	public PizzaOrderDto orderPizza(String name) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		PizzaOrderDto pizzaOrderDto = null;
		try {
			pizzaOrderDto = new PizzaOrderDto(name, Instant.now().toEpochMilli());
			//file = new File(getClass().getClassLoader().getResource("pizzaorders.txt").toURI().getPath());
			file = new File(destinationPath);
		    fw = new FileWriter(file, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println(pizzaOrderDto.getName()+","+pizzaOrderDto.getTime());
		    out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return pizzaOrderDto;
	}
}
