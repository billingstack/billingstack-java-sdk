package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Customer {

	private String id;
	
	@JsonProperty("merchant_id")
	private String merchant;
	
	private String name;
	
	private String title;
	
	@JsonProperty("language_id")
	private String language;
	
	@JsonProperty("currency_id")
	private String currency;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
