package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class PaymentMethod {

	private String id;
	
	@JsonProperty("provider_id")
	private String provider;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
