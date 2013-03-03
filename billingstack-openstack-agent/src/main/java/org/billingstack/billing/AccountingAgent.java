package org.billingstack.billing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.billingstack.BillingStack;
import org.billingstack.Customer;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Product;
import org.billingstack.Subscription;
import org.billingstack.SubscriptionTarget;
import org.billingstack.Usage;

public class AccountingAgent {
	
	private static final String ENDPOINT = "http://localhost:8080/billingstack";

	public static void main(String[] args) {
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			List<Product> products = mt.products().list();
			
			List<Customer> customers = mt.customers().list();
			
			for(Customer c : customers) {
				
				System.out.println(c);
				
				CustomerTarget ct = mt.customer(c.getId());
				
				List<Subscription> subscriptions = ct.subscriptions().list();
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					SubscriptionTarget st = ct.subscription(s.getId());
					
					Plan plan = mt.plan(s.getPlan()).show();
					
					for(int i = 0; i < 10; i++) {
						
						//pull metering
						
						final Product product = findByName(products, "instance:m1.tiny");
						
						
						
						if(product != null) {
							
							final PlanItem planItem = find(plan, product);
							
							//actually it would be needed to split a ProviderUsage 
							//in BillingStack usages since time ranges have different prices 
							//per range
							
							final BigDecimal volume = new BigDecimal(14);
							
							final BigDecimal unitPrice = findPrice(planItem,volume);
							
							Usage u = new Usage(){{
								setProduct(product.getId());
								setStart(new Date().getTime());
								setEnd(new Date().getTime() - 10000);
								setVolume(new BigDecimal(14));
								setPrice(unitPrice);
								setTotal(volume.multiply(unitPrice));
							}};
							
							//push billing
							
							st.usages().create(u);
							
						} else {
							//alert a usage detected without product in billingstack!
						}
						
					}
					
				}
				
				System.out.println(products);
				
			}
			
		}
		
	}
	
	private static final Product findByName(List<Product> products, String name) {
		for(Product p : products) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	private static final PlanItem find(Plan plan, Product product) {
		for(PlanItem pi : plan.getItems()) {
			System.out.println(pi.getProduct());
			System.out.println(product.getId());
			if(pi.getProduct().equals(product.getId())) {
				return pi;
			}
		}
		return null;
	}
	
	private static final BigDecimal findPrice(PlanItem planItem, BigDecimal volume) {
		//find price for volume
		return BigDecimal.ONE;
	}
	
}
