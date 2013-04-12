package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Invoice;
import org.billingstack.InvoiceLine;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.User;

public class InvoicesExample {
	
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
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
