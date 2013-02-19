package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class CustomerPaymentMethodTarget {
	
	private WebTarget target;

	public CustomerPaymentMethodTarget(WebTarget target, String customerPaymentMethodId) {
		this.target = target.path("payment-methods").path(customerPaymentMethodId);
	}
	
	public CustomerPaymentMethod show() {
		return target.request().get(CustomerPaymentMethod.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
