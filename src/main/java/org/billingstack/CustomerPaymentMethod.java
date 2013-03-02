package org.billingstack;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class CustomerPaymentMethod {

	private String id;
	
	@JsonProperty("method_id")
	private String method;
	
	private String customer;

	private Map<String, Object> metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
	
}
