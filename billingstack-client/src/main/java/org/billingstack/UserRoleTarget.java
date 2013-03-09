package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class UserRoleTarget {
	
	private WebTarget target;

	public UserRoleTarget(WebTarget target, String roleId) {
		this.target = target.path("roles").path(roleId);
	}
	
	public Response create() {
		return target.request().put(Entity.text("0"), Response.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
