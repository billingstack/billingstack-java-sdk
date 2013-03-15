package org.billingstack;

import java.util.List;

public class BillingStackinvoicingAgent {
	
	public static final String BILLINGSTACK_ENDPOINT = "http://localhost:8080/billingstack-api";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BillingStackEndpoint bs = new BillingStack().create(BILLINGSTACK_ENDPOINT);
		
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
				
				CustomerTarget ct = mt.customer(c.getId());
				
				List<Subscription> subscriptions = ct.subscriptions().list();
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					SubscriptionTarget st = ct.subscription(s.getId());
					
					List<Usage> usage = st.usages().list();
					
					for(final Usage u : usage) {
						bs.merchant(m.getId()).invoice(i.getId()).lines().create(new InvoiceLine() {{
							setDescription(u.getProduct());
							setQuantity(u.getVolume());
							setPrice(u.getPrice());
							setSubtotal(u.getTotal());
						}});
					}

				}
			}
		}

	}

}
