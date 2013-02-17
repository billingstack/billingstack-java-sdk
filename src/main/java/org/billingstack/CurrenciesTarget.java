package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class CurrenciesTarget {

	private WebTarget target;

	public CurrenciesTarget(WebTarget target) {
		this.target = target.path("currencies");
	}
	
	public List<Currency> list() {
		return target.request().get(new GenericType<List<Currency>>(){});
	}
	
	public Currency create(Currency currency) {
		return target.request().post(Entity.json(currency),Currency.class);
	}
	
}
