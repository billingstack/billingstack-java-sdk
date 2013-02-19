package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PaymentMethodsTarget {
	
	private WebTarget target;

	public PaymentMethodsTarget(WebTarget target) {
		this.target = target.path("payment-methods");
	}
	
	public List<PaymentMethod> list() {
		return target.request().get(new GenericType<List<PaymentMethod>>(){});
	}
	
	public PaymentMethod create(PaymentMethod customer) {
		return target.request().post(Entity.json(customer),PaymentMethod.class);
	}

}
