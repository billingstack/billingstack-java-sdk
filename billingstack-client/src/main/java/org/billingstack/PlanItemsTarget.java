package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PlanItemsTarget {
	
	private WebTarget target;

	public PlanItemsTarget(WebTarget target) {
		this.target = target.path("items");
	}
	
	public List<PlanItem> list() {
		return target.request().get(new GenericType<List<PlanItem>>(){});
	}
	
	public PlanItem create(PlanItem plan) {
		return target.request().post(Entity.json(plan), PlanItem.class);
	}

}
