package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Subscription {

	private String id;
	
	@JsonProperty("customer_id")
	private String customer;
	
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	@JsonProperty("plan_id")
	private String plan;
	
	private String resource;
	
	@JsonProperty("billing_day")
	private Integer billingDay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", customer=" + customer
				+ ", paymentMethod=" + paymentMethod + ", plan=" + plan
				+ ", resource=" + resource + ", billingDay=" + billingDay + "]";
	}
	
}
