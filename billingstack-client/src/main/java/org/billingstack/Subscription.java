package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Subscription {

	private String id;
	
	@JsonProperty("customer_id")
	private String customerId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_title")
	private String customerTitle;
	
	@JsonProperty("plan_id")
	private String planId;
	
	@JsonProperty("plan_name")
	private String planName;
	
	@JsonProperty("plan_title")
	private String planTitle;
	
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerTitle
	 */
	public String getCustomerTitle() {
		return customerTitle;
	}

	/**
	 * @param customerTitle the customerTitle to set
	 */
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	/**
	 * @return the planId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
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

	/**
	 * @return the planTitle
	 */
	public String getPlanTitle() {
		return planTitle;
	}

	/**
	 * @param planTitle the planTitle to set
	 */
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", customerTitle="
				+ customerTitle + ", planId=" + planId + ", resource="
				+ resource + ", paymentMethod=" + paymentMethod
				+ ", billingDay=" + billingDay + "]";
	}

}
