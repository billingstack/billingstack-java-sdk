package org.billingstack.examples;

import java.util.HashMap;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.Currency;
import org.billingstack.Customer;
import org.billingstack.CustomerPaymentMethod;
import org.billingstack.CustomerTarget;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Plan;
import org.billingstack.Product;
import org.billingstack.Subscription;
import org.billingstack.User;

public class BillingStackExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingStack bs = new BillingStack(ENDPOINT);
		
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
		
		final PaymentGatewayProvider pgp = bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setMetadata(new HashMap<String, String>() {{
				put("test", "value1");
			}});
		}});
		
		final PaymentMethod pgm = bs.paymentGatewayProvider(pgp.getId()).paymentMethods().create(new PaymentMethod() {{
			setName("visa");
		}});
		
		bs.paymentGatewayProviders().list();
		
		bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage(languages.get(0).getId());
			setCurrency(currencies.get(0).getId());
		}});
		
		bs.merchants().create(new Merchant() {{
			setName("openstackbiller");
			setTitle("OpenStack Biller");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		/*
		m.show();
//		m.delete();
		*/
		User merchantUser = m.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		
		m.users().list();
//		m.user(merchantUser.getId()).show();
//		m.user(merchantUser.getId()).delete();
		
		m.products().create(new Product() {{
			setName("instance:m1.tiny");
			setTitle("instance:m1.tiny");
		}});
		m.products().create(new Product() {{
			setName("instance:m1.small");
			setTitle("instance:m1.small");
		}});
		m.products().list();

//		bs.merchant("123").product("456").show();
//		bs.merchant("123").product("456").delete();
		
		final Plan plan = m.plans().create(new Plan() {{
			setName("plan.s");
			setTitle("Plan S");
		}});
		m.plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});	
		m.plans().list();
//		bs.merchant("123").plan("456").show();
//		bs.merchant("123").plan("456").delete();
		
		m.paymentGateways().create(new PaymentGateway() {{
			setProvider("braintree");
			setTitle("My Braintree");
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		
		m.paymentGateways().list();
		
		
//		bs.merchant("123").paymentGateway("456").show();
//		bs.merchant("123").paymentGateway("456").delete();
		
		Customer customer = m.customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		CustomerTarget c = m.customer(customer.getId());
		
		m.customers().list();
//		bs.merchant("123").customer("456").show();
//		bs.merchant("123").customer("456").delete();
		
		final CustomerPaymentMethod cpm = c.paymentMethods().create(new CustomerPaymentMethod() {{
			setMethod(pgm.getId());
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		c.paymentMethods().list();
//		bs.merchant("123").customer("456").paymentMethod("789").show();
//		bs.merchant("123").customer("456").paymentMethod("789").delete();
		
		c.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		c.users().list();
//		bs.merchant("123").customer("456").user("789").show();
//		bs.merchant("123").customer("456").user("789").delete();
		
		c.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlan(plan.getId());
			setResource("tenant:1234");
			
		}});
		c.subscriptions().list();
//		bs.merchant("123").customer("456").subscription("789").show();
//		bs.merchant("123").customer("456").subscription("789").delete();
		
//		bs.merchant("123").customer("456").invoices().list();
//		bs.merchant("123").customer("456").invoices().create(new Invoice());
//		bs.merchant("123").customer("456").invoice("789").show();
//		bs.merchant("123").customer("456").invoice("789").delete();
	}

}
