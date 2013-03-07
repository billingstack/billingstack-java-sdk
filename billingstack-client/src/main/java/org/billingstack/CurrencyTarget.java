package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class CurrencyTarget {
	
	private WebTarget target;

	public CurrencyTarget(WebTarget target, String name) {
		this.target = target.path("currencies").path(name);
	}
	
	public Currency show() {
		return target.request().get(Currency.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
