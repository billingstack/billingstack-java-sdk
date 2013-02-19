package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class CustomerPaymentMethodsTarget {
	
	private WebTarget target;

	public CustomerPaymentMethodsTarget(WebTarget target) {
		this.target = target.path("payment-methods");
	}
	
	public List<CustomerPaymentMethod> list() {
		return target.request().get(new GenericType<List<CustomerPaymentMethod>>(){});
	}
	
	public CustomerPaymentMethod create(CustomerPaymentMethod user) {
		return target.request().post(Entity.json(user), CustomerPaymentMethod.class);
	}
	
}
