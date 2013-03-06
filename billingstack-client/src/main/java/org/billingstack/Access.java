package org.billingstack;

import org.codehaus.jackson.annotate.JsonProperty;

public class Access {

	private String token;
	
	private String endpoint;
	
	@JsonProperty("merchant_id")
	private String merchant;
	
	@JsonProperty("merchant_name")
	private String merchantName;
	
	@JsonProperty("merchant_endpoint")
	private String merchantEndpoint;
	
	@JsonProperty("customer_id")
	private String customer;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_endpoint")
	private String customerEndpoint;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @return the merchant
	 */
	public String getMerchant() {
		return merchant;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @return the merchantEndpoint
	 */
	public String getMerchantEndpoint() {
		return merchantEndpoint;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return the customerEndpoint
	 */
	public String getCustomerEndpoint() {
		return customerEndpoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Access [token=" + token + ", endpoint=" + endpoint
				+ ", merchant=" + merchant + ", merchantName=" + merchantName
				+ ", merchantEndpoint=" + merchantEndpoint + ", customer="
				+ customer + ", customerName=" + customerName
				+ ", customerEndpoint=" + customerEndpoint + "]";
	}
	
}
