package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Product {
	
	private String id;
	
	@JsonProperty("merchant_id")
	private String merchant;

	private String name;
	
	private String title;
	
	private String description;
	
	private String provider;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", merchant=" + merchant + ", name="
				+ name + ", title=" + title + ", description=" + description
				+ ", provider=" + provider + "]";
	}
	
}
