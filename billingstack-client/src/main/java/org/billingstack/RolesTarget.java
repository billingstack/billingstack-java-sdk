package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class RolesTarget {

	private WebTarget target;

	public RolesTarget(WebTarget target) {
		this.target = target.path("roles");
	}
	
	public List<Role> list() {
		return target.request().get(new GenericType<List<Role>>(){});
	}
	
	public Role create(Role language) {
		return target.request().post(Entity.json(language),Role.class);
	}
	
}
