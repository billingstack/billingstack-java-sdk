package org.billingstack.examples;

import org.billingstack.BillingStack;
import org.billingstack.Customer;
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
		
		/*
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
		
		bs.merchants().create(new Merchant() {{
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
		*/	
//		bs.merchant("123").show();
//		bs.merchant("123").delete();
		
		bs.merchant("402881a33ce9cac2013ce9cb36380004").users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		bs.merchant("402881a33ce9cac2013ce9cb36380004").users().list();	
//		bs.merchant("123").user("456").show();
//		bs.merchant("123").user("456").delete();
		
		bs.merchant("123").products().create(new Product() {{
			setName("instance:m1.tiny");
			setTitle("instance:m1.tiny");
		}});
		bs.merchant("123").products().create(new Product() {{
			setName("instance:m1.small");
			setTitle("instance:m1.small");
		}});
		bs.merchant("123").products().list();
//		bs.merchant("123").product("456").show();
//		bs.merchant("123").product("456").delete();
		
		bs.merchant("123").plans().create(new Plan() {{
			setName("plan.s");
			setTitle("Plan S");
		}});
		bs.merchant("123").plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});	
		bs.merchant("123").plans().list();
//		bs.merchant("123").plan("456").show();
//		bs.merchant("123").plan("456").delete();
		
//		bs.merchant("123").paymentGateways().list();
//		bs.merchant("123").paymentGateways().create(new PaymentGateway());
//		bs.merchant("123").paymentGateway("456").show();
//		bs.merchant("123").paymentGateway("456").delete();
		
		
		bs.merchant("123").customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		bs.merchant("123").customers().list();	
//		bs.merchant("123").customer("456").show();
//		bs.merchant("123").customer("456").delete();
		
//		bs.merchant("123").customer("456").paymentMethods().list();
//		bs.merchant("123").customer("456").paymentMethods().create(new PaymentMethod());
//		bs.merchant("123").customer("456").paymentMethod("789").show();
//		bs.merchant("123").customer("456").paymentMethod("789").delete();
		
		bs.merchant("123").customer("456").users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		bs.merchant("123").customer("456").users().list();
//		bs.merchant("123").customer("456").user("789").show();
//		bs.merchant("123").customer("456").user("789").delete();
		
		bs.merchant("123").customer("456").subscriptions().create(new Subscription() {{
			setPlan("");
			setResource("tenant:1234");
		}});
		bs.merchant("123").customer("456").subscriptions().list();
//		bs.merchant("123").customer("456").subscription("789").show();
//		bs.merchant("123").customer("456").subscription("789").delete();
		
//		bs.merchant("123").customer("456").invoices().list();
//		bs.merchant("123").customer("456").invoices().create(new Invoice());
//		bs.merchant("123").customer("456").invoice("789").show();
//		bs.merchant("123").customer("456").invoice("789").delete();
		

	}

}
