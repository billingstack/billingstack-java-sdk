package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class AccountsTarget {
	
	private WebTarget target;

	public AccountsTarget(WebTarget target) {
		this.target = target.path("accounts");
	}
	
	public List<Account> list() {
		return target.request().get(new GenericType<List<Account>>(){});
	}
	
	public Account create(Account account) {
		return target.request().post(Entity.json(account),Account.class);
	}

}
