package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class InvoicesTarget {
	
	private WebTarget target;

	public InvoicesTarget(WebTarget target) {
		this.target = target.path("invoices");
	}
	
	public List<Invoice> list() {
		return target.request().get(new GenericType<List<Invoice>>(){});
	}
	
	public Invoice create(Invoice invoice) {
		return target.request().post(Entity.json(invoice),Invoice.class);
	}
	
}
