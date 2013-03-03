package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class PaymentGatewayTarget {
	
	private WebTarget target;

	public PaymentGatewayTarget(WebTarget target, String paymentMethodId) {
		this.target = target.path("payment-gateways").path(paymentMethodId);
	}
	
	public PaymentGateway show() {
		return target.request().get(PaymentGateway.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
