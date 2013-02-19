package org.billingstack.examples;

import java.util.HashMap;

import org.billingstack.BillingStack;
import org.billingstack.Currency;
import org.billingstack.Customer;
import org.billingstack.CustomerPaymentMethod;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.PaymentGatewayProvider;
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
			setLetter("en");
			setName("English");
		}});
		bs.languages().create(new Language() {{
			setLetter("es");
			setName("Spanish");
		}});
		
		
		bs.languages().list();
		
		bs.currencies().create(new Currency() {{
			setLetter("usd");
			setName("US Dollar");
		}});
		bs.currencies().create(new Currency() {{
			setLetter("eur");
			setName("Euro");
		}});
		bs.currencies().list();
		
		bs.paymentGatewayProviders().create(new PaymentGatewayProvider() {{
			setName("braintree");
			setTitle("Braintree");
			setDescription("Braintree Payments");
			setDefault(Boolean.TRUE);
			setMetadata(new HashMap<String, String>() {{
				put("test", "value1");
			}});
		}});
		
		bs.paymentGatewayProviders().list();
		
		Merchant merchant = bs.merchants().create(new Merchant() {{
			setName("billingstack");
			setTitle("BillingStack");
			setLanguage("en");
			setCurrency("usd");
		}});
		
		bs.merchants().create(new Merchant() {{
			setName("openstackbiller");
			setTitle("OpenStack Biller");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchant.getId());
//		bs.merchant("123").show();
//		bs.merchant("123").delete();
		
		m.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		
		m.users().list();	
//		bs.merchant("123").user("456").show();
//		bs.merchant("123").user("456").delete();
		
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
		
		m.plans().create(new Plan() {{
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
			setDefault(Boolean.TRUE);
			setData(new HashMap<String, Object>() {{
				
			}});
		}});
		m.paymentGateways().list();
//		bs.merchant("123").paymentGateway("456").show();
//		bs.merchant("123").paymentGateway("456").delete();
		
		m.customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		m.customers().list();	
//		bs.merchant("123").customer("456").show();
//		bs.merchant("123").customer("456").delete();
		
		m.customer("456").paymentMethods().create(new CustomerPaymentMethod() {{
			setPaymentMethod("payment_method_id");
			setData(new HashMap<String, Object>() {{
				
			}});
		}});
		m.customer("456").paymentMethods().list();
//		bs.merchant("123").customer("456").paymentMethod("789").show();
//		bs.merchant("123").customer("456").paymentMethod("789").delete();
		
		m.customer("456").users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		m.customer("456").users().list();
//		bs.merchant("123").customer("456").user("789").show();
//		bs.merchant("123").customer("456").user("789").delete();
		
		m.customer("456").subscriptions().create(new Subscription() {{
			setPlan("");
			setResource("tenant:1234");
		}});
		m.customer("456").subscriptions().list();
//		bs.merchant("123").customer("456").subscription("789").show();
//		bs.merchant("123").customer("456").subscription("789").delete();
		
//		bs.merchant("123").customer("456").invoices().list();
//		bs.merchant("123").customer("456").invoices().create(new Invoice());
//		bs.merchant("123").customer("456").invoice("789").show();
//		bs.merchant("123").customer("456").invoice("789").delete();
		

	}

}
