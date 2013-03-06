package org.billingstack;

import java.util.List;

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

}
