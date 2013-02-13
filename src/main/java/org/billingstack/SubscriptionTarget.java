package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class SubscriptionTarget {
	
	private WebTarget target;

	public SubscriptionTarget(WebTarget target, String subscriptionId) {
		this.target = target.path("subscriptions").path(subscriptionId);
	}
	
	public Subscription show() {
		return target.request().get(Subscription.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
}
