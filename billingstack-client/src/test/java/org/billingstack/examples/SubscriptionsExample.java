package org.billingstack.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.CustomerPaymentMethod;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.PaymentGatewayProvider;
import org.billingstack.Plan;
import org.billingstack.Subscription;

public class SubscriptionsExample {
	
	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
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
		
		m.subscriptions().create(new Subscription() {{
			setPaymentMethod(cpm.getId());
			setPlanId(plans.get(0).getId());
			setResourceType("tenant");
			setResourceId("1234");
			
		}});
		List<Subscription> subscriptions = m.subscriptions().list();
		m.subscription(subscriptions.get(0).getId()).show();

		client.close();
	}

}
