package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class PlansTarget {
	
	private WebTarget target;

	public PlansTarget(WebTarget target) {
		this.target = target.path("plans");
	}
	
	public List<Plan> list() {
		return target.request().get(new GenericType<List<Plan>>(){});
	}
	
	public Plan create(Plan plan) {
		return target.request().post(Entity.json(plan), Plan.class);
	}

}
