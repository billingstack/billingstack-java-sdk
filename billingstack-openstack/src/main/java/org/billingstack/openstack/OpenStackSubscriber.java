package org.billingstack.openstack;

import java.io.FileInputStream;
import java.util.Properties;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Merchant;
import org.billingstack.Plan;
import org.billingstack.Subscription;
import org.openstack.keystone.Keystone;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Tenant;
import org.openstack.keystone.model.authentication.UsernamePassword;

public class OpenStackSubscriber {

	public static void main(String[] args) throws Exception {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/billingstack.properties"));
		
		BillingStack client = new BillingStack(properties);
		BillingStackEndpoint bs = client.create();
		
		//we will use the first available merchant, actually this should be passed as args
		Merchant merchant = bs.merchants().list().get(0);
		
		//we will use the first available customer, actually this should be passed as args
		final Customer customer = bs.merchant(merchant.getId()).customers().list().get(0);
		
		//we will use the first available plan, actually this should be passed as args
		final Plan plan = bs.merchant(merchant.getId()).plans().list().get(0);
		
		Subscription subscription = bs.merchant(merchant.getId()).subscriptions().create(new Subscription() {{
			setCustomerId(customer.getId());
			setPlanId(plan.getId());
		}});
		
		Keystone keystone = new Keystone(properties.getProperty("identity.endpoint"));
		
		Access access = keystone.tokens()
				.authenticate(new UsernamePassword(properties.getProperty("keystone.username"), properties.getProperty("keystone.password")))
				.withTenantName("admin")
				.execute();
		
		Tenant tenant = new Tenant();
		tenant.setName(subscription.getId());
		tenant.setDescription(merchant.getName()+"-"+customer.getName()+"-"+plan.getName());
		tenant.setEnabled(true);
		keystone = new Keystone(properties.getProperty("keystone.endpoint"));
		keystone.token(access.getToken().getId());
		tenant = keystone.tenants().create(tenant).execute();
		
		//create a openstack user from customer admin
		
		subscription.setResourceType("tenant");
		subscription.setResourceId(tenant.getId());
		
		bs.merchant(merchant.getId()).subscription(subscription.getId()).update(subscription);
		
		client.close();
		
	}
	
}
