package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class RoleTarget {
	
	private WebTarget target;

	public RoleTarget(WebTarget target, String roleId) {
		this.target = target.path("roles").path(roleId);
	}
	
	public Role show() {
		return target.request().get(Role.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
