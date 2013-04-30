package org.billingstack.openstack;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.CustomerTarget;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Subscription;
import org.billingstack.SubscriptionTarget;
import org.billingstack.Usage;
import org.openstack.ceilometer.Ceilometer;
import org.openstack.ceilometer.v2.model.Statistics;
import org.openstack.keystone.Keystone;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.authentication.UsernamePassword;

public class OpenStackMediator {

	public static void main(String[] args) throws IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		Keystone keystone = new Keystone(properties.getProperty("identity.endpoint"));
		//keystone.enableLogging(Logger.getLogger("keystone"), 100 * 1024);
		
		Access access = keystone.tokens()
				.authenticate(new UsernamePassword(properties.getProperty("keystone.username"), properties.getProperty("keystone.password")))
				.withTenantName("admin")
				.execute();
		
		Ceilometer ceilometer = new Ceilometer(properties.getProperty("metering.endpoint"));
		ceilometer.token(access.getToken().getId());
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			//List<Product> products = mt.products().list();
			
			List<Customer> customers = mt.customers().list();
			
			for(Customer c : customers) {
				
				System.out.println(c);
				
				CustomerTarget ct = mt.customer(c.getId());
				
				List<Subscription> subscriptions = mt.subscriptions().list();
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					
					SubscriptionTarget st = mt.subscription(s.getId());
					
					// i get the plan from subscription
					//here only one shoot to db and i have all the plan
					//no joins
					//in a heavy load env this is good :)
					Plan plan = mt.plan(s.getPlanId()).show();
					
					//for each plan item
					for(final PlanItem pi : plan.getItems()) {
			
						//pull metering, i need to do this using product name, which is what ceilometer understand
						//also i'm filtering using project_id (in ceilometer) that is the resource id in billingstack
						List<Statistics> stats = null; //ceilometer.execute(new MeterStatistics().name("").eq("project_id", "948eeb593acd4223ad572c49e1ef5709"));
						
						//then i get the statistics for a product
						for(final Statistics stat : stats) {
							
							//actually here i need to get the price for the volume, now i'm mocking!
							final BigDecimal unitPrice = BigDecimal.ONE;
							
							//here i create a billingstack usage from ceilometer stats and billingstack price
							Usage u = new Usage(){{
								//setProduct(pi.getProduct());
								setStart(stat.getPeriodStart());
								setEnd(stat.getPeriodEnd());
								setVolume(stat.getSum());
								setPrice(unitPrice);
								setTotal(stat.getSum().multiply(unitPrice));
							}};
							
							//push billing, here i push to billingstack
							System.out.println(u);
							//st.usages().create(u);
							
							//that's all
						
						}
				
					}

				}
			}
		}
		
		client.close();
		
	}

}
