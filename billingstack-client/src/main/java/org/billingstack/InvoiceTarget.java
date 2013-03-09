package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class InvoiceTarget {
	
	private WebTarget target;

	public InvoiceTarget(WebTarget target, String invoiceId) {
		this.target = target.path("invoices").path(invoiceId);
	}
	
	public Invoice show() {
		return target.request().get(Invoice.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public InvoiceLinesTarget lines() {
		return new InvoiceLinesTarget(target);
	}
	
	public InvoiceLineTarget line(String invoiceLineId) {
		return new InvoiceLineTarget(target, invoiceLineId);
	}
	
}
