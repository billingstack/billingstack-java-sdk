package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Currency;
import org.billingstack.InvoiceState;
import org.billingstack.Language;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Role;

public class BootstrapExample {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		bs.roles().create(new Role() {{
			setName("billingstack_admin");
		}});
		
		bs.roles().create(new Role() {{
			setName("merchant_admin");
		}});
		
		
		bs.roles().create(new Role() {{
			setName("customer_admin");
		}});
		
		final List<Role> roles = bs.roles().list();
		
		bs.languages().create(new Language() {{
			setName("en");
			setTitle("English");
		}});
		bs.languages().create(new Language() {{
			setName("es");
			setTitle("Spanish");
		}});
		
		final List<Language> languages = bs.languages().list();
		
		bs.currencies().create(new Currency() {{
			setName("usd");
			setTitle("US Dollar");
		}});
		bs.currencies().create(new Currency() {{
			setName("eur");
			setTitle("Euro");
		}});
		
		final List<Currency> currencies = bs.currencies().list();
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("pending");
		}});
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("completed");
		}});
		
		bs.invoiceStates().create(new InvoiceState() {{
			setName("failed");
		}});
		
		final List<InvoiceState> invoiceStates = bs.invoiceStates().list();
		
		bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setProperties(new HashMap<String, Object>() {{
				put("k.1", "v.1");
				put("k.2", "v.2");
			}});
		}});
		
		final List<PaymentGatewayProvider> pgps = bs.paymentGatewayProviders().list();
		
		final PaymentMethod pgm = bs.paymentGatewayProvider(pgps.get(0).getId()).paymentMethods().create(new PaymentMethod() {{
			setType("creditcard");
			setName("visa");
			setTitle("VISA");
			setProperties(new HashMap<String, Object>(){{
				put("k.1", "v.1");
				put("k.2", "v.2");
			}});
		}});
		
		client.close();
		
	}

}
