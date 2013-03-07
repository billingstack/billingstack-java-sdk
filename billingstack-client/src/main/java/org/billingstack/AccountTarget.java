package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class AccountTarget {
	
	private WebTarget target;

	public AccountTarget(WebTarget target, String accountId) {
		this.target = target.path("accounts").path(accountId);
	}
	
	public Account show() {
		return target.request().get(Account.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public UsersTarget users() {
		return new UsersTarget(target);
	}
	
	public UserTarget user(String userId) {
		return new UserTarget(target, userId);
	}
	
}
