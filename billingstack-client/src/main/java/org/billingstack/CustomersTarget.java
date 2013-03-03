package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class CustomersTarget {
	
	private WebTarget target;

	public CustomersTarget(WebTarget target) {
		this.target = target.path("customers");
	}
	
	public List<Customer> list() {
		return target.request().get(new GenericType<List<Customer>>(){});
	}
	
	public Customer create(Customer customer) {
		return target.request().post(Entity.json(customer),Customer.class);
	}

}
