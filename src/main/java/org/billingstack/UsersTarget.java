package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class UsersTarget {
	
	private WebTarget target;

	public UsersTarget(WebTarget target) {
		this.target = target.path("users");
	}
	
	public Users list() {
		return target.request().get(Users.class);
	}
	
	public User create(User user) {
		return target.request().post(Entity.json(user), User.class);
	}
	
}
