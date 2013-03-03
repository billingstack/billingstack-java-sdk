package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class PlanItemTarget {
	
	private WebTarget target;

	public PlanItemTarget(WebTarget target, String planItemId) {
		this.target = target.path("items").path(planItemId);
	}
	
	public PlanItem show() {
		return target.request().get(PlanItem.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	/*
	public SubscriptionsTarget subscriptions() {
		return new SubscriptionsTarget(target);
	}
	
	public SubscriptionTarget subscription(String subscriptionId) {
		return new SubscriptionTarget(target, subscriptionId);
	}
	*/

}
