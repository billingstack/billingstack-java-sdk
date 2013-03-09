package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class PlanItemTarget {
	
	private WebTarget target;

	public PlanItemTarget(WebTarget target, String productId) {
		this.target = target.path("items").path(productId);
	}
	
	public PlanItem create(PlanItem planItem) {
		return target.request().put(Entity.json(planItem), PlanItem.class);
	}
	
	public PlanItem show() {
		return target.request().get(PlanItem.class);
	}
	
	public PlanItem update(PlanItem planItem) {
		return target.request().method("PATCH", Entity.json(planItem), PlanItem.class);
	}
	
	public void delete() {
		target.request().delete();
	}

}
