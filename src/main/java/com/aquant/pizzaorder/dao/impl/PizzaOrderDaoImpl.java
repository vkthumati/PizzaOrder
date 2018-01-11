package com.aquant.pizzaorder.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.aquant.pizzaorder.dao.IPizzaOrderDao;
import com.aquant.pizzaorder.model.PizzaOrderDto;

@Repository
public class PizzaOrderDaoImpl implements IPizzaOrderDao {
	@Override
	public Set<PizzaOrderDto> getAllPizzaOrders() {
		Set<PizzaOrderDto> pizzaOrders=null;
		try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader().getResource("pizzaorders.txt").toURI()), StandardCharsets.UTF_8)) {
			  pizzaOrders = lines.map(line -> {
				  String orderDetails[] = line.split(",");
				  return new PizzaOrderDto(orderDetails[0], Long.valueOf(orderDetails[1]));
			  }).collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException | URISyntaxException e) {
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
			file = new File(getClass().getClassLoader().getResource("pizzaorders.txt").toURI().getPath());
		    fw = new FileWriter(file, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println(pizzaOrderDto.getName()+","+pizzaOrderDto.getTime());
		    out.flush();
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		finally {
		    if(out != null)
		            out.close();
		    try {
		        if(bw != null)
		            bw.close();
		    } catch (IOException e) {
		    		e.printStackTrace();
		    }
		    try {
		        if(fw != null)
		            fw.close();
		    } catch (IOException e) {
		    		e.printStackTrace();
		    }
		}
		return pizzaOrderDto;
	}
}
