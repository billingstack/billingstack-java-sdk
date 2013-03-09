package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class InvoiceLinesTarget {
	
	private WebTarget target;

	public InvoiceLinesTarget(WebTarget target) {
		this.target = target.path("items");
	}
	
	public List<PlanItem> list() {
		return target.request().get(new GenericType<List<PlanItem>>(){});
	}
	
	public InvoiceLine create(InvoiceLine invoiceLine) {
		return target.request().post(Entity.json(invoiceLine),InvoiceLine.class);
	}

}
