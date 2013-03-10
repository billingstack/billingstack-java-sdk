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

public class SubscriptionsExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		
		final List<Customer> customers = m.customers().list();
		
		CustomerTarget c = m.customer(customers.get(0).getId());
		
		final List<PaymentGatewayProvider> pgps = bs.paymentGatewayProviders().list();
		
		final CustomerPaymentMethod cpm = c.paymentMethods().create(new CustomerPaymentMethod() {{
			setMethod(pgps.get(0).getMethods().get(0).getId());
			setMetadata(new HashMap<String, Object>() {{
				
			}});
		}});
		
		final List<Plan> plans = m.plans().list();
		
		//List<CustomerPaymentMethod> customerPaymentMethods = c.paymentMethods().list();
		
		c.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlan(plans.get(0).getId());
			setResource("tenant:1234");
			
		}});
		List<Subscription> subscriptions = c.subscriptions().list();
		c.subscription(subscriptions.get(0).getId()).show();

	}

}
