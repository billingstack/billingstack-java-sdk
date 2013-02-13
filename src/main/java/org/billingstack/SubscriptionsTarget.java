package org.billingstack;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class SubscriptionsTarget {
	
	private WebTarget target;

	public SubscriptionsTarget(WebTarget target) {
		this.target = target.path("subscriptions");
	}
	
	public Subscriptions list() {
		return target.request().get(Subscriptions.class);
	}
	
	public Subscription create(Subscription subscription) {
		return target.request().post(Entity.json(null),Subscription.class);
	}
	
}
