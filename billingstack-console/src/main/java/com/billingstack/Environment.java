package com.billingstack;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

public class Environment {
	
	private BillingStack client = new BillingStack();
	
	private BillingStackEndpoint billingStack = client.create("http://localhost:8080/billingstack-api");

	/**
	 * @return the billingStack
	 */
	public BillingStackEndpoint getBillingStack() {
		return billingStack;
	}

	/**
	 * @param billingStack the billingStack to set
	 */
	public void setBillingStack(BillingStackEndpoint billingStack) {
		this.billingStack = billingStack;
	}
	
	public void destroy() {
		client.close();
	}
	
}
