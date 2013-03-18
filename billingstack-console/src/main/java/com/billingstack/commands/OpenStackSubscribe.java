package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Customer;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.Subscription;
import org.openstack.keystone.KeystoneClient;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.api.CreateTenant;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.Tenant;
import org.openstack.keystone.model.User;

import com.billingstack.Environment;

public class OpenStackSubscribe extends MerchantCommand {
	
	public OpenStackSubscribe() {
		super("openstack-subscribe");
	}
	/**
	 * This method is executed when billingstack>openstack-subscribe ....
	 * 
	 */
	@Override
	public void execute(Environment env, final CommandLine cmd) {
		
		MerchantTarget mt = getMerchant(env, cmd);
		
		//we have merchant
		final Merchant merchant = mt.show();
		
		//we have customer
		final Customer customer = mt.customer(cmd.getOptionValue("customer")).show();
		
		//we have the selected plan for the subscription
		final Plan plan = mt.plan(cmd.getOptionValue("plan")).show();
		
		//we create a billingstack subscription
		Subscription subscription = mt.subscriptions().create(new Subscription() {{
			setCustomer(customer.getId());
			setPlan(plan.getId());
		}});
		
		//now we should go to openstack / keystone
		KeystoneClient keystone = new KeystoneClient((String) env.getProperty("keystone.endpoint"));
		
		Access access = keystone.execute(Authenticate.withPasswordCredentials(
				(String) env.getProperty("keystone.admin.username"), 
				(String) env.getProperty("keystone.admin.password")
		).withTenantName("keystone.admin.tenant.name"));
		
		//we create a tenant for the subscription
		Tenant tenant = new Tenant();
		tenant.setName(subscription.getId());
		tenant.setDescription(merchant.getName()+"-"+customer.getName()+"-"+plan.getName());
		tenant.setEnabled(true);
		keystone = new KeystoneClient((String) env.getProperty("keystone.endpoint"), access.getToken().getId());
		tenant = keystone.execute(new CreateTenant(tenant));
		
		//we need an openstack user (find or create) from billingstack users .... (this is tricky)
		//since i don't know the password in billingstack (we have all encrypted) 
		//when we are a customer, we are in an account not in a user, so ...
		//...... ARGGHHGHG
		//look
		User user = new User();
		user.setName("");
		user.setEmail("");
		user.setUsername("");
		user.setPassword("");
		user.setEnabled(Boolean.TRUE);
		
		subscription.setResource(tenant.getId());
		
		mt.subscription(subscription.getId()).update(subscription);
	}
	
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "customer", true, "customer id");
		opts.addOption(null, "plan", true, "plan id");
		return opts;
	}

}
