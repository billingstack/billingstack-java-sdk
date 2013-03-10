package org.billingstack.examples;

import org.billingstack.Authentication;
import org.billingstack.BillingStack;

public class AuthenticationExample {

	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		bs.authenticate(new Authentication(){{
			setAccount("billingstack");
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		bs.close();

	}

}
