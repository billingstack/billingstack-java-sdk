package org.billingstack.examples;

import org.billingstack.Authentication;
import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

public class AuthenticationExample {

	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack client = new BillingStack();
		BillingStackEndpoint bs = client.create(ENDPOINT);
		
		client.authenticate(new Authentication(){{
			setAccount("billingstack");
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		client.close();

	}

}
