package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class InvoicesTarget {
	
	private WebTarget target;

	public InvoicesTarget(WebTarget target) {
		this.target = target.path("invoices");
	}
	
	public Invoices list() {
		return target.request().get(Invoices.class);
	}
	
	public Invoice create(Invoice invoice) {
		return target.request().post(Entity.json(invoice),Invoice.class);
	}
	
}
