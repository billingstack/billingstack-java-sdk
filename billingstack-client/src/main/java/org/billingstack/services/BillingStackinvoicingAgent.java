package org.billingstack.services;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Invoice;
import org.billingstack.InvoiceLine;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Subscription;
import org.billingstack.SubscriptionTarget;
import org.billingstack.SubscriptionsTarget;
import org.billingstack.Usage;

public class BillingStackinvoicingAgent {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStackEndpoint bs = new BillingStack(properties).create();
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			List<Customer> customers = mt.customers().list();
			
			for(final Customer c : customers) {

				final Invoice i = bs.merchant(m.getId()).invoices().create(new Invoice() {{
					setCustomer(c.getId());
				}});
				
				System.out.println(c);
				
				List<Subscription> subscriptions = mt.subscriptions().list(SubscriptionsTarget.query().customer(c.getId()));
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					SubscriptionTarget st = mt.subscription(s.getId());
					
					/*
					List<Usage> usage = st.usages().list();
					
					for(final Usage u : usage) {
						bs.merchant(m.getId()).invoice(i.getId()).lines().create(new InvoiceLine() {{
							setDescription(u.getProductId());
							setQuantity(u.getVolume());
							setPrice(u.getPrice());
							setSubtotal(u.getTotal());
						}});
					}
					*/
				}
			}
		}

	}

}
