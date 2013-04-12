package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;

public class CustomersExample {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();	
		
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
