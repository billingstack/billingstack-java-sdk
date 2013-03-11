package com.billingstack;

import org.billingstack.BillingStack;

public class Environment {
	
	private BillingStack billingStack = new BillingStack("http://localhost:8080/billingstack-api");

	/**
	 * @return the billingStack
	 */
	public BillingStack getBillingStack() {
		return billingStack;
	}

	/**
	 * @param billingStack the billingStack to set
	 */
	public void setBillingStack(BillingStack billingStack) {
		this.billingStack = billingStack;
	}
	
}
