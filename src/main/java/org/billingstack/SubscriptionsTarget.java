package org.billingstack;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class SubscriptionsTarget {
	
	private WebTarget target;

	public SubscriptionsTarget(WebTarget target) {
		this.target = target.path("subscriptions");
	}
	
	public List<Subscription> list() {
		return target.request().get(new GenericType<List<Subscription>>(){});
	}
	
	public Subscription create(Subscription subscription) {
		return target.request().post(Entity.json(null),Subscription.class);
	}
	
}
