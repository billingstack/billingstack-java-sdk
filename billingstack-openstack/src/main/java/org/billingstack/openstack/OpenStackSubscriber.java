package org.billingstack.openstack;

import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.billingstack.Customer;
import org.billingstack.Merchant;
import org.billingstack.Plan;
import org.billingstack.Subscription;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.api.CreateTenant;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Tenant;

public class OpenStackSubscriber {

	public static void main(String[] args) throws Exception {
		
		BillingStack client = new BillingStack();
		BillingStackEndpoint bs = client.create(Configuration.BILLINGSTACK_ENDPOINT);
		
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
		
		KeystoneClient keystone = new KeystoneClient(Configuration.IDENTITY_ENDPOINT);
		
		Access access = keystone.execute(Authenticate.withPasswordCredentials(
				Configuration.KEYSTONE_USERNAME, 
				Configuration.KEYSTONE_PASSWORD)
		);
		
		access = keystone.execute(Authenticate.withToken(access.getToken().getId()).withTenantName(
				Configuration.KEYSTONE_ADMIN_TENANT_NANME)
		);
		
		Tenant tenant = new Tenant();
		tenant.setName(subscription.getId());
		tenant.setDescription(merchant.getName()+"-"+customer.getName()+"-"+plan.getName());
		tenant.setEnabled(true);
		keystone = new KeystoneClient(Configuration.KEYSTONE_ENDPOINT);
		keystone.token(access.getToken().getId());
		tenant = keystone.execute(new CreateTenant(tenant));
		
		//create a openstack user from customer admin
		
		subscription.setResource(tenant.getId());
		
		bs.merchant(merchant.getId()).subscription(subscription.getId()).update(subscription);
		
		client.close();
		
	}
	
}
