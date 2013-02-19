package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PaymentGatewayProvidersTarget {
	
	private WebTarget target;

	public PaymentGatewayProvidersTarget(WebTarget target) {
		this.target = target.path("payment-gateway-providers");
	}
	
	public List<PaymentGatewayProvider> list() {
		return target.request().get(new GenericType<List<PaymentGatewayProvider>>(){});
	}
	
	public PaymentGatewayProvider create(PaymentGatewayProvider paymentGatewayProvider) {
		return target.request().post(Entity.json(paymentGatewayProvider),PaymentGatewayProvider.class);
	}

}
