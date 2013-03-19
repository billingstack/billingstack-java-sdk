package org.billingstack.examples;

import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;

public class CustomersExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack client = new BillingStack();
		BillingStackEndpoint bs = client.create(ENDPOINT);
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		
		Customer customer = m.customers().create(new Customer() {{
			setName("woorea");
			setName("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		CustomerTarget c = m.customer(customer.getId());
		
		List<Customer> customers = m.customers().list();
		c.show();
		
		client.close();

	}

}
