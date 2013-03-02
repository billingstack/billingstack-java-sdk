package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class PlanItem {
	
	private String type;

	private String id;
	
	@JsonProperty("merchant_id")
	private String merchant;
	
	@JsonProperty("product_id")
	private String product;
	
	private String title;
	
	public PlanItem(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
