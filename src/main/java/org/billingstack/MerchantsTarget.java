package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class MerchantsTarget {
	
	private WebTarget target;
	
	public MerchantsTarget(WebTarget target) {
		this.target = target.path("merchants");
	}

	public Merchants list() {
		return target.request().get(Merchants.class);
	}
	
	public Merchant create(Merchant merchant) {
		return target.request().post(Entity.json(merchant),Merchant.class);
	}
	
}
