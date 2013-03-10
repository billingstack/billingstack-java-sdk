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

public class CustomersExample {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack-api";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		final List<Merchant> merchants = bs.merchants().list();
		
		MerchantTarget m = bs.merchant(merchants.get(0).getId());
		
		User user = bs.users().create(new User() {{
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
		
		bs.close();

	}

}
