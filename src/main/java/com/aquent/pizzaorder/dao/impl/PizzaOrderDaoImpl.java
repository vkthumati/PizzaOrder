package com.aquent.pizzaorder.dao.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.aquent.pizzaorder.dao.IPizzaOrderDao;
import com.aquent.pizzaorder.model.PizzaOrderDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PizzaOrderDaoImpl implements IPizzaOrderDao {
	/*@Value("${systemProperties['source.path']?:'sampl'}")*/
	private String soursePath;
	
	/*@Value("${systemProperties['destination.path']?:'sample'}")*/
	private String destinationPath;
	
	@Autowired
	public PizzaOrderDaoImpl(Environment env) {
		soursePath = env.getProperty("source.path");
		destinationPath=env.getProperty("destination.path");
	}
	
	@Override
	public void loadPizzaOrdersData() {
		log.info("Destination Path ::::::::::::::::::: "+soursePath);
		log.info("Source Path ::::::::::::::::::: "+destinationPath);
		File file = new File(destinationPath);
		if(file.exists())
			file.delete();
		try (	
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out =  new PrintWriter(bw);
				Stream<String> lines = Files.lines(Paths.get(soursePath))
			) {
			file.createNewFile();
			
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
		Set<PizzaOrderDto> pizzaOrders=null;
		try (
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
		//file = new File(getClass().getClassLoader().getResource("pizzaorders.txt").toURI().getPath());
		File file = new File(destinationPath);
		PizzaOrderDto pizzaOrderDto = null;
		try (
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out =  new PrintWriter(bw);
			){
			pizzaOrderDto = new PizzaOrderDto(name, Instant.now().toEpochMilli());
		    out.println(pizzaOrderDto.getName()+","+pizzaOrderDto.getTime());
		    out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return pizzaOrderDto;
	}
}
