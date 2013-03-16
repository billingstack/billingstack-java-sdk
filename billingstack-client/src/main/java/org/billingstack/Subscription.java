package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Subscription {

	private String id;
	
	@JsonProperty("customer_id")
	private String customer;
	
	@JsonProperty("plan_id")
	private String plan;
	
	private String resource;
	
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	@JsonProperty("billing_day")
	private Integer billingDay;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the billingDay
	 */
	public Integer getBillingDay() {
		return billingDay;
	}

	/**
	 * @param billingDay the billingDay to set
	 */
	public void setBillingDay(Integer billingDay) {
		this.billingDay = billingDay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", customer=" + customer + ", plan="
				+ plan + ", resource=" + resource + ", paymentMethod="
				+ paymentMethod + ", billingDay=" + billingDay + "]";
	}

}
