package org.billingstack.examples;

import java.math.BigDecimal;

import org.billingstack.Account;
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
		
		User user = bs.users().create(new User() {{
			setUsername("luis0");
			setPassword("secret0");
		}});
		
		final Account account0 = bs.accounts().create(new Account(){{
			setName("billingstack");
			setTitle("BillingStack");
		}});
		
		Merchant merchant = bs.merchants().create(new Merchant() {{
			setId(account0.getId());
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.account(account0.getId()).user(user.getId()).role(bs.roles().list().get(0).getId()).create();
		
		MerchantTarget m = bs.merchant(merchant.getId());
		
		user = bs.users().create(new User() {{
			setUsername("luis11");
			setPassword("secret0");
		}});
		
		final Account account1 = bs.accounts().create(new Account(){{
			setName("woorea");
			setTitle("Woorea");
		}});
		
		final Customer customer = m.customers().create(new Customer() {{
			setId(account1.getId());
			setLanguage("es");
			setCurrency("eur");
		}});
		
		bs.account(account1.getId()).user(user.getId()).role(bs.roles().list().get(0).getId()).create();
		
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
