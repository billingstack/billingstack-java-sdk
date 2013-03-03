package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class InvoiceStateTarget {
	
	private WebTarget target;

	public InvoiceStateTarget(WebTarget target, String invoiceStateId) {
		this.target = target.path("invoice-states").path(invoiceStateId);
	}
	
	public InvoiceState show() {
		return target.request().get(InvoiceState.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
