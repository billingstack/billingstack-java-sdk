package org.billingstack;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Access {

	private String token;
	
	private String endpoint;
	
	@JsonProperty("account_id")
	private String accountId;
	
	@JsonProperty("account_name")
	private String accountName;
	
	@JsonProperty("account_roles")
	private List<String> accountRoles;

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
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @return the accountRoles
	 */
	public List<String> getAccountRoles() {
		return accountRoles;
	}
	
}
