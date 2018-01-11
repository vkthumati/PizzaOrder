package com.aquant.pizzaorder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PizzaOrderDto implements Comparable<PizzaOrderDto>{
	private String name;
	private long time;

	@Override
	public int compareTo(PizzaOrderDto o) {
		return o.getTime()>this.getTime()?1:(o.getTime()<this.getTime()?-1:0);
	}
}
