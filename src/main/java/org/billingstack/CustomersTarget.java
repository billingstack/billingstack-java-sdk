package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class CustomersTarget {
	
	private WebTarget target;

	public CustomersTarget(WebTarget target) {
		this.target = target.path("customers");
	}
	
	public Customers list() {
		return target.request().get(Customers.class);
	}
	
	public Customer create(Customer customer) {
		return target.request().post(Entity.json(customer),Customer.class);
	}

}
