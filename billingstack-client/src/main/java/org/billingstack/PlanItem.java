package org.billingstack;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlanItem {
	
	@JsonProperty("merchant_id")
	private String merchantId;

	@JsonProperty("plan_id")
	private String planId;;
	
	@JsonProperty("product_id")
	private String productId;
	
	private String provider;
	
	private String source;
	
	private String name;
	
	private String title;
	
	private String description;

	private List<Pricing> pricing;

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pricing
	 */
	public List<Pricing> getPricing() {
		return pricing;
	}

	/**
	 * @param pricing the pricing to set
	 */
	public void setPricing(List<Pricing> pricing) {
		this.pricing = pricing;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlanItem [merchantId=" + merchantId + ", planId=" + planId
				+ ", productId=" + productId + ", provider=" + provider
				+ ", source=" + source + ", name=" + name + ", title=" + title
				+ ", description=" + description + ", pricing=" + pricing + "]";
	}

}
