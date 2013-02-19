package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class PaymentGatewayProviderTarget {
	
	private WebTarget target;

	public PaymentGatewayProviderTarget(WebTarget target, String paymentGatewayProviderId) {
		this.target = target.path("payment-gateway-providers").path(paymentGatewayProviderId);
	}
	
	public PaymentGatewayProvider show() {
		return target.request().get(PaymentGatewayProvider.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
