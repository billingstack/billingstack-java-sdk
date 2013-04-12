package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.billingstack.Authentication;
import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

public class AuthenticationExample {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		client.authenticate(new Authentication(){{
			setAccount("billingstack");
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		client.close();

	}

}
