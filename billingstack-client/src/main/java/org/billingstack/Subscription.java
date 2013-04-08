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
	
	@JsonProperty("resource_id")
	private String resourceId;
	
	@JsonProperty("resource_type")
	private String resourceType;
	
	@JsonProperty("payment_method_id")
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
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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

}
