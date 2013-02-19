package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PaymentGatewaysTarget {
	
	private WebTarget target;

	public PaymentGatewaysTarget(WebTarget target) {
		this.target = target.path("payment-gateways");
	}
	
	public List<PaymentGateway> list() {
		return target.request().get(new GenericType<List<PaymentGateway>>(){});
	}
	
	public PaymentGateway create(PaymentGateway customer) {
		return target.request().post(Entity.json(customer),PaymentGateway.class);
	}

}
