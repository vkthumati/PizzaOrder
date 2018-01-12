package com.aquent.pizzaorder.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aquent.pizzaorder.exceptions.ErrorResponse;
import com.aquent.pizzaorder.facade.IPizzaOrderFacade;
import com.aquent.pizzaorder.model.PizzaOrderDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1")
public class PizzaOrderController {

	@Autowired
	private IPizzaOrderFacade pizzaOrderFacade;

	@ApiOperation(nickname = "getAllPizzaOrders", value = "List All Pizza Orders", notes = "This Api retrieves all pizza orders.", response = PizzaOrderDto.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Error processing request", response = ErrorResponse.class), })
	@RequestMapping(value = "/allOrders", method = RequestMethod.GET)
	public ResponseEntity<Set<PizzaOrderDto>> getAllPizzaOrders() {
		Set<PizzaOrderDto> pizzaOrders = pizzaOrderFacade.getAllPizzaOrders();
		if (pizzaOrders.isEmpty()) {
			return new ResponseEntity<Set<PizzaOrderDto>>(HttpStatus.NO_CONTENT);// You many decide to return
		}
		return new ResponseEntity<Set<PizzaOrderDto>>(pizzaOrders, HttpStatus.OK);
	}

	@RequestMapping(value = "/pizzaOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PizzaOrderDto> orderPizza(@RequestBody String name) {
		PizzaOrderDto pizzaOrderDto = pizzaOrderFacade.orderPizza(name);
		ResponseEntity<PizzaOrderDto> response = null;
		if (pizzaOrderDto == null) {
			response = new ResponseEntity<PizzaOrderDto>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<PizzaOrderDto>(pizzaOrderDto, HttpStatus.CREATED);
		}
		return response;
	}
}
