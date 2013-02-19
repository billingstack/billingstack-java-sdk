package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class UsagesTarget {
	
	private WebTarget target;

	public UsagesTarget(WebTarget target) {
		this.target = target.path("usage");
	}
	
	public List<Usage> list() {
		return target.request().get(new GenericType<List<Usage>>(){});
	}
	
	public Usage create() {
		return target.request().post(Entity.json(null), Usage.class);
	}
	
}
