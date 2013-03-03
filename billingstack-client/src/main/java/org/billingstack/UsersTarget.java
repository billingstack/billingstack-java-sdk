package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class UsersTarget {
	
	private WebTarget target;

	public UsersTarget(WebTarget target) {
		this.target = target.path("users");
	}
	
	public List<User> list() {
		return target.request().get(new GenericType<List<User>>(){});
	}
	
	public User create(User user) {
		return target.request().post(Entity.json(user), User.class);
	}
	
}
