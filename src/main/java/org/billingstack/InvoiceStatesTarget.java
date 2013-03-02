package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class InvoiceStatesTarget {

	private WebTarget target;

	public InvoiceStatesTarget(WebTarget target) {
		this.target = target.path("invoice-states");
	}
	
	public List<InvoiceState> list() {
		return target.request().get(new GenericType<List<InvoiceState>>(){});
	}
	
	public InvoiceState create(InvoiceState language) {
		return target.request().post(Entity.json(language),InvoiceState.class);
	}
	
}
