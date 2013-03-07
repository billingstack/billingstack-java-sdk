package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class UserTarget {
	
	private WebTarget target;

	public UserTarget(WebTarget target, String userId) {
		this.target = target.path("users").path(userId);
	}
	
	public User show() {
		return target.request().get(User.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public UserRolesTarget roles() {
		return new UserRolesTarget(target);
	}
	
	public UserRoleTarget role(String roleId) {
		return new UserRoleTarget(target, roleId);
	}
	
}
