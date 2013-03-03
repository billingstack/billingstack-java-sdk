package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class MerchantsTarget {
	
	private WebTarget target;
	
	public MerchantsTarget(WebTarget target) {
		this.target = target.path("merchants");
	}

	public List<Merchant> list() {
		return target.request().get(new GenericType<List<Merchant>>(){});
	}
	
	public Merchant create(Merchant merchant) {
		return target.request().post(Entity.json(merchant),Merchant.class);
	}
	
}
