package org.billingstack.examples;

import java.math.BigDecimal;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Invoice;
import org.billingstack.InvoiceLine;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.User;

public class InvoicesExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack client = new BillingStack();
		BillingStackEndpoint bs = client.create(ENDPOINT);
		
		Merchant merchant = bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage("en");
			setCurrency("usd");
		}});
		
		User user = bs.merchant(merchant.getId()).users().create(new User() {{
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		bs.merchant(merchant.getId()).user(user.getId()).role(bs.roles().list().get(1).getId()).create();
		
		MerchantTarget m = bs.merchant(merchant.getId());
		
		final Customer customer = m.customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		final Invoice i = m.invoices().create(new Invoice() {{
			setCustomer(customer.getId());
			setCurrency("usd");
		}});
		
		m.invoice(i.getId()).lines().create(new InvoiceLine() {{
			setDescription("description.i");
			setQuantity(new BigDecimal("1"));
			setPrice(new BigDecimal("1"));
			setSubtotal(new BigDecimal("1"));
		}});
		
		client.close();

	}

}
