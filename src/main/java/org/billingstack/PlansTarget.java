package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class PlansTarget {
	
	private WebTarget target;

	public PlansTarget(WebTarget target) {
		this.target = target.path("plans");
	}
	
	public Plans list() {
		return target.request().get(Plans.class);
	}
	
	public Plan create(Plan plan) {
		return target.request().post(Entity.json(plan), Plan.class);
	}

}
