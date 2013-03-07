package org.billingstack;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class UserRolesTarget {
	
	private WebTarget target;

	public UserRolesTarget(WebTarget target) {
		this.target = target.path("roles");
	}
	
	public List<Role> list() {
		return target.request().get(new GenericType<List<Role>>(){});
	}
	
}
