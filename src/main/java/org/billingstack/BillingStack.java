package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class BillingStack {
	
	private WebTarget endpoint;

	public MerchantsTarget merchants() {
		return new MerchantsTarget(endpoint);
	}
	
	public MerchantTarget merchant(String merchantId) {
		return new MerchantTarget(endpoint, merchantId);
	}
	
}
