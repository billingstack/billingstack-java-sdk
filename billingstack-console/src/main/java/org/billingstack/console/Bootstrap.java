package org.billingstack.console;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Currency;
import org.billingstack.Customer;
import org.billingstack.InvoiceState;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Plan;
import org.billingstack.Role;
import org.billingstack.Subscription;

public class Bootstrap extends BillingStackCommand {

	public Bootstrap() {
		super("bootstrap");
	}

	@Override
	public void execute(BillingStackEndpoint bs, final CommandLine cmd) {
		
		bs.roles().create(new Role() {{
			setName("billingstack_admin");
		}});
		
		bs.roles().create(new Role() {{
			setName("merchant_admin");
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
		/*
		bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setProperties(new HashMap<String, String>() {{
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
		*/
		
		final Merchant merchant = bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage("en");
			setCurrency("usd");
		}});
		
		final Plan plan = bs.merchant(merchant.getId()).plans().create(new Plan() {{
			setName("plan.xs");
			setTitle("XS");
		}});
		
		final Customer customer = bs.merchant(merchant.getId()).customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea Solutions, S.L");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		final Subscription subscription = bs.merchant(merchant.getId()).subscriptions().create(new Subscription() {{
			setCustomerId(customer.getId());
			setPlanId(plan.getId());
		}});
		
	}

}
