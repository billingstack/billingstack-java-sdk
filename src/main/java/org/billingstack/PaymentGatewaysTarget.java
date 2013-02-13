package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class PaymentGatewaysTarget {
	
	private WebTarget target;

	public PaymentGatewaysTarget(WebTarget target) {
		this.target = target.path("payment-gateways");
	}
	
	public PaymentGateways list() {
		return target.request().get(PaymentGateways.class);
	}
	
	public PaymentGateway create(PaymentGateway customer) {
		return target.request().post(Entity.json(customer),PaymentGateway.class);
	}

}
