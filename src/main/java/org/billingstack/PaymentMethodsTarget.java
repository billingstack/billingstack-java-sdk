package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class PaymentMethodsTarget {
	
	private WebTarget target;

	public PaymentMethodsTarget(WebTarget target) {
		this.target = target.path("payment-methods");
	}
	
	public PaymentMethods list() {
		return target.request().get(PaymentMethods.class);
	}
	
	public PaymentMethod create(PaymentMethod customer) {
		return target.request().post(Entity.json(customer),PaymentMethod.class);
	}

}
