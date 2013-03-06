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
import org.openstack.ceilometer.CeilometerClient;
import org.openstack.ceilometer.v2.api.MeterStatistics;
import org.openstack.ceilometer.v2.model.Statistics;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Authentication;
import org.openstack.keystone.model.Authentication.PasswordCredentials;

public class AccountingAgent {

	private static final String[] PRODUCTS = { "storage.objects",
			"storage.objects.containers", "storage.objects.size", "image",
			"image.size", "image.upload", "image.update", "image.download",
			"image.serve", "image", "image.size", "image.upload",
			"image.update", "image.download", "image.serve", "image",
			"image.size", "image.upload", "image.update", "image.download",
			"image.serve", "instance", "instance:m1.tiny", "disk.read.bytes",
			"disk.read.requests", "disk.write.bytes", "disk.write.requests",
			"cpu", "cpu_util", "network.incoming.bytes",
			"network.incoming.packets", "network.outgoing.bytes",
			"network.outgoing.packets" };

	private static final String KEYSTONE_AUTH_URL = "";

	private static final String KEYSTONE_USERNAME = "admin";

	private static final String KEYSTONE_PASSWORD = "secret0";
	
	private static final String CEILOMETER_ENDPOINT = "";

	private static final String ENDPOINT = "http://localhost:8080/billingstack";

	public static void main(String[] args) {
		
		KeystoneClient keystone = new KeystoneClient(KEYSTONE_AUTH_URL);
		//keystone.enableLogging(Logger.getLogger("keystone"), 100 * 1024);
		Authentication authentication = new Authentication();
		PasswordCredentials passwordCredentials = new PasswordCredentials();
		passwordCredentials.setUsername(KEYSTONE_USERNAME);
		passwordCredentials.setPassword(KEYSTONE_PASSWORD);
		authentication.setTenantName("admin");
		authentication.setPasswordCredentials(passwordCredentials);
		
		//access with unscoped token
		Access access = keystone.execute(new Authenticate(authentication));
		
		CeilometerClient ceilometer = new CeilometerClient(CEILOMETER_ENDPOINT, access.getToken().getId());
		
		BillingStack bs = new BillingStack(ENDPOINT);
		
		List<Merchant> merchants = bs.merchants().list();
		
		for(Merchant m : merchants) {
			
			System.out.println(m);
			
			MerchantTarget mt = bs.merchant(m.getId());
			
			//List<Product> products = mt.products().list();
			
			List<Customer> customers = mt.customers().list();
			
			for(Customer c : customers) {
				
				System.out.println(c);
				
				CustomerTarget ct = mt.customer(c.getId());
				
				List<Subscription> subscriptions = ct.subscriptions().list();
				
				for(Subscription s : subscriptions) {
					
					System.out.println(s);
					
					
					SubscriptionTarget st = ct.subscription(s.getId());
					
					// i get the plan from subscription
					Plan plan = mt.plan(s.getPlan()).show();
					
					//for each plan item
					for(final PlanItem pi : plan.getItems()) {
			
						//pull metering, i need to do this using product name, which is what ceilometer understand
						//also i'm filtering using project_id (in ceilometer) that is the resource id in billingstack
						List<Statistics> stats = ceilometer.execute(new MeterStatistics().name("").eq("project_id", "948eeb593acd4223ad572c49e1ef5709"));
						
						//then i get the statistics for a product
						for(final Statistics stat : stats) {
							
							//actually here i need to get the price for the volume, now i'm mocking!
							final BigDecimal unitPrice = BigDecimal.ONE; //findPrice(planItem,volume);
							
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
	}

	private static final Product findByName(List<Product> products, String name) {
		for (Product p : products) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	private static final BigDecimal findPrice(PlanItem planItem,
			BigDecimal volume) {
		// find price for volume
		return BigDecimal.ONE;
	}

}
