package org.billingstack.examples;

import java.util.HashMap;
import java.util.List;

import org.billingstack.Authentication;
import org.billingstack.BillingStack;
import org.billingstack.Currency;
import org.billingstack.InvoiceState;
import org.billingstack.Language;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Role;
import org.billingstack.User;

public class BillingStackExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingStack bs = new BillingStack(ENDPOINT);
		
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
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		m.show();
		
		m.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		
		final List<User> merchantUsers = m.users().list();
		m.user(merchantUsers.get(0).getId()).show();

		bs.authenticate(new Authentication(){{
			setMerchant(merchants.get(0).getId());
			setUsername("luis");
			setPassword("secret0");
		}});
		
		/*
		m.products().create(new Product() {{
			setName("instance:m1.tiny");
			setTitle("instance:m1.tiny");
		}});
		m.products().create(new Product() {{
			setName("instance:m1.small");
			setTitle("instance:m1.small");
		}});
		
		final List<Product> products = m.products().list();

		m.product(products.get(0).getId()).show();

		m.plans().create(new Plan() {{
			setName("plan.m");
			setTitle("Plan M");
		}});
		
		final List<Plan> plans = m.plans().list();
		
		
		
		m.plan(plans.get(0).getId()).item(products.get(0).getId()).create(new FixedPlanItem(){{
			setPrice(new BigDecimal("0.99"));
		}});
		
		m.plan(plans.get(0).getId()).item(products.get(0).getId()).update(new FixedPlanItem(){{
			setPrice(new BigDecimal("1.99"));
		}});
		
		m.plan(plans.get(0).getId()).item(products.get(0).getId()).create(new VolumePlanItem(){{
			setPricing(new ArrayList<VolumeRangePricing>() {{
				VolumeRangePricing pricing0 = new VolumeRangePricing();
				pricing0.setEnd(new BigDecimal(9.99));
				pricing0.setPrice(new BigDecimal(10.00));
				add(pricing0);
				VolumeRangePricing pricing1 = new VolumeRangePricing();
				pricing1.setStart(new BigDecimal(10.00));
				pricing1.setEnd(new BigDecimal(19.99));
				pricing1.setPrice(new BigDecimal(8.00));
				add(pricing1);
				VolumeRangePricing pricing2 = new VolumeRangePricing();
				pricing2.setStart(new BigDecimal(20.00));
				pricing2.setPrice(new BigDecimal(5.00));
				add(pricing2);
			}});
		}});
		
		m.plan(plans.get(0).getId()).item(products.get(0).getId()).create(new TimePlanItem(){{
			setPricing(new ArrayList<TimeRangePricing>() {{
				TimeRangePricing pricing0 = new TimeRangePricing();
				pricing0.setStart("08:00");
				pricing0.setEnd("14:59");
				pricing0.setPrice(new BigDecimal(8.00));
				add(pricing0);
				TimeRangePricing pricing1 = new TimeRangePricing();
				pricing1.setStart("15:00");
				pricing1.setEnd("07:59");
				pricing1.setPrice(new BigDecimal(8.00));
				add(pricing1);
			}});
		}});
		
		m.plan(plans.get(0).getId()).show();
		
		m.paymentGateways().create(new PaymentGateway() {{
			setProvider("braintree");
			setTitle("My Braintree");
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<PaymentGateway> paymentGateways = m.paymentGateways().list();
		
		m.paymentGateway(paymentGateways.get(0).getId()).show();
		
		Customer customer = m.customers().create(new Customer() {{
			setName("woorea");
			setTitle("Woorea");
			setLanguage("es");
			setCurrency("eur");
		}});
		
		CustomerTarget c = m.customer(customer.getId());
		
		List<Customer> customers = m.customers().list();
		c.show();
		
		final CustomerPaymentMethod cpm = c.paymentMethods().create(new CustomerPaymentMethod() {{
			setMethod(pgm.getId());
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<CustomerPaymentMethod> customerPaymentMethods = c.paymentMethods().list();
		
		c.users().create(new User() {{
			setUsername("luis");
			setPassword("secret0");
		}});
		List<User> users = c.users().list();
		c.user(users.get(0).getId()).show();
		
		c.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlan(plans.get(0).getId());
			setResource("tenant:1234");
			
		}});
		List<Subscription> subscriptions = c.subscriptions().list();
		c.subscription(subscriptions.get(0).getId()).show();
		*/

	}

}
