package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class PaymentMethodTarget {
	
	private WebTarget target;

	public PaymentMethodTarget(WebTarget target, String paymentMethodId) {
		this.target = target.path("payment-methods").path(paymentMethodId);
	}
	
	public PaymentMethod show() {
		return target.request().get(PaymentMethod.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
