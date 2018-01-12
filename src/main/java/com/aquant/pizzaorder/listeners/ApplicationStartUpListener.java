package com.aquant.pizzaorder.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.aquant.pizzaorder.facade.IPizzaOrderFacade;

@Component
public class ApplicationStartUpListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private IPizzaOrderFacade pizzaOrderFacade;

	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		pizzaOrderFacade.loadPizzaOrdersData();	
	}
}