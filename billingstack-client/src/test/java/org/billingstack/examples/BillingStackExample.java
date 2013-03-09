package org.billingstack.examples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.billingstack.Account;
import org.billingstack.BillingStack;
import org.billingstack.Customer;
import org.billingstack.CustomerPaymentMethod;
import org.billingstack.CustomerTarget;
import org.billingstack.FixedPlanItem;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGateway;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.PaymentMethod;
import org.billingstack.Plan;
import org.billingstack.Product;
import org.billingstack.Subscription;
import org.billingstack.TimePlanItem;
import org.billingstack.TimeRangePricing;
import org.billingstack.User;
import org.billingstack.VolumePlanItem;
import org.billingstack.VolumeRangePricing;

public class BillingStackExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
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
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		m.show();
		
		final List<User> merchantUsers = bs.account(merchants.get(0).getId()).users().list();
		
		
		bs.user(merchantUsers.get(0).getId()).show();

		/*
		bs.authenticate(new Authentication(){{
			setMerchant(merchants.get(0).getId());
			setUsername("luis");
			setPassword("secret0");
		}});
		*/
		
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
		
		PaymentGateway pg = m.paymentGateways().create(new PaymentGateway() {{
			setProvider("braintree");
			setTitle("My Braintree");
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<PaymentGateway> paymentGateways = m.paymentGateways().list();
		
		m.paymentGateway(paymentGateways.get(0).getId()).show();
		
		user = bs.users().create(new User() {{
			setUsername("luis11");
			setPassword("secret0");
		}});
		
		final Account account1 = bs.accounts().create(new Account(){{
			setName("woorea");
			setTitle("Woorea");
		}});
		
		Customer customer = m.customers().create(new Customer() {{
			setId(account1.getId());
			setLanguage("es");
			setCurrency("eur");
		}});
		
		bs.account(account1.getId()).user(user.getId()).role(bs.roles().list().get(0).getId()).create();
		
		final List<User> customerUsers = bs.account(customer.getId()).users().list();
		
		CustomerTarget c = m.customer(customer.getId());
		
		List<Customer> customers = m.customers().list();
		c.show();
		
		final List<PaymentGatewayProvider> pgps = bs.paymentGatewayProviders().list();
		
		final CustomerPaymentMethod cpm = c.paymentMethods().create(new CustomerPaymentMethod() {{
			setMethod(pgps.get(0).getMethods().get(0).getId());
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		List<CustomerPaymentMethod> customerPaymentMethods = c.paymentMethods().list();
		
		c.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlan(plans.get(0).getId());
			setResource("tenant:1234");
			
		}});
		List<Subscription> subscriptions = c.subscriptions().list();
		c.subscription(subscriptions.get(0).getId()).show();

	}

}
