package org.billingstack;

import javax.ws.rs.client.WebTarget;

public class PlanTarget {
	
	private WebTarget target;

	public PlanTarget(WebTarget target, String planId) {
		this.target = target.path("plans").path(planId);
	}
	
	public Plan show() {
		return target.request().get(Plan.class);
	}
	
	public void delete() {
		target.request().delete();
	}
	
	public PlanItemsTarget items() {
		return new PlanItemsTarget(target);
	}
	
	public PlanItemTarget item(String productId) {
		return new PlanItemTarget(target, productId);
	}
	
	public SubscriptionsTarget subscriptions() {
		return new SubscriptionsTarget(target);
	}
	
	public SubscriptionTarget subscription(String subscriptionId) {
		return new SubscriptionTarget(target, subscriptionId);
	}

}
