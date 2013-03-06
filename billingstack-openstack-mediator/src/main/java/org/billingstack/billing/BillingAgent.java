package org.billingstack.billing;

import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.Customer;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.Subscription;
import org.billingstack.SubscriptionTarget;
import org.billingstack.Usage;

public class BillingAgent {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack";

	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			List<Customer> customers = mt.customers().list();
			
			for(Customer c : customers) {
				
				System.out.println(c);
				
				CustomerTarget ct = mt.customer(c.getId());
				
				List<Subscription> subscriptions = ct.subscriptions().list();
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					SubscriptionTarget st = ct.subscription(s.getId());
					
					Plan plan = mt.plan(s.getPlan()).show();
					
					List<Usage> usage = st.usages().list();
					
					for(Usage u : usage) {
						
						System.out.println(u);
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
