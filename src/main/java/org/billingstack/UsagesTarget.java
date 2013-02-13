package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class UsagesTarget {
	
	private WebTarget target;

	public UsagesTarget(WebTarget target) {
		this.target = target.path("usage");
	}
	
	public Usages list() {
		return target.request().get(Usages.class);
	}
	
	public Usage create() {
		return target.request().post(Entity.json(null), Usage.class);
	}
	
}
