package org.billingstack.billing;

import java.math.BigDecimal;
import java.util.List;

import org.billingstack.Plan;
import org.billingstack.PlanItem;
import org.billingstack.Product;
import org.openstack.ceilometer.CeilometerClient;
import org.openstack.ceilometer.v2.api.MeterList;
import org.openstack.ceilometer.v2.api.MeterShow;
import org.openstack.ceilometer.v2.api.MeterStatistics;
import org.openstack.ceilometer.v2.api.ResourceList;
import org.openstack.ceilometer.v2.api.ResourceShow;
import org.openstack.ceilometer.v2.model.Meter;
import org.openstack.ceilometer.v2.model.Resource;
import org.openstack.ceilometer.v2.model.Sample;
import org.openstack.ceilometer.v2.model.Statistics;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Authentication;
import org.openstack.keystone.model.Authentication.PasswordCredentials;

public class AccountingAgent {
	
	private static final String KEYSTONE_AUTH_URL = "";
	
	private static final String KEYSTONE_USERNAME = "admin";
	
	private static final String KEYSTONE_PASSWORD = "secret0";
	
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
		
		CeilometerClient ceilometer = new CeilometerClient("", access.getToken().getId());
		
		List<Resource> resources = ceilometer.execute(new ResourceList().eq("resource_id", "23b55841eedd41e99d5f3f32149ca086"));
		
		
		for(Resource r : resources) {
			Resource resource = ceilometer.execute(new ResourceShow().id(r.getResource()));
		}
		
		List<Meter> meters = ceilometer.execute(new MeterList());
		
		for(Meter m : meters) {
			List<Sample> samples = ceilometer.execute(new MeterShow().name(m.getName()));
			List<Statistics> stats = ceilometer.execute(new MeterStatistics().name(m.getName()));
			System.out.println(m.getName());
			System.out.println(stats);
		}
		
		/*
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
		*/
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
