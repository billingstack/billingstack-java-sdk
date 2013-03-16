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
		return get(target);
	}
	
	public List<Subscription> list(QueryParameters query) {
		return get(query.apply(target));
	}
	
	public Subscription create(Subscription subscription) {
		return target.request().post(Entity.json(subscription),Subscription.class);
	}
	
	public static QueryParameters query() {
		return new QueryParameters();
	}
	
	public static class QueryParameters {
		
		private String customerId;
		
		private String planId;
		
		public QueryParameters customer(String customerId) {
			return this;
		}
		
		public QueryParameters plan(String planId) {
			return this;
		}
		
		public WebTarget apply(WebTarget target) {
			if(customerId != null) {
				target = target.queryParam("customer_id", customerId);
			}
			if(planId != null) {
				target = target.queryParam("plan_id", planId);
			}
			return target;
		}
		
	}
	
	private List<Subscription> get(WebTarget target) {
		return target.request().get(new GenericType<List<Subscription>>(){});
	}
	
}
