package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.Customer;
import org.billingstack.Merchant;
import org.billingstack.MerchantTarget;
import org.billingstack.Plan;
import org.billingstack.Subscription;
import org.openstack.keystone.Keystone;
import org.openstack.keystone.model.Role;
import org.openstack.keystone.model.Roles;
import org.openstack.keystone.model.Tenant;
import org.openstack.keystone.model.User;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class OpenStackSubscribe extends MerchantCommand {
	
	public OpenStackSubscribe() {
		super("openstack-subscribe");
	}
	/**
	 * This method is executed when billingstack>openstack-subscribe ....
	 * 
	 */
	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		
		Keystone keystone = env.keystone();
		
		Roles roles = keystone.roles().list().execute();
		
		Role admin = Iterables.find(roles, new Predicate<Role>() {
			
			public boolean apply(Role role) {
				return "admin".equals(role.getName());
			}
			
		});
		
		User user = keystone.users().show(cmd.getOptionValue("user")).execute();
		
		MerchantTarget mt = env.ENDPOINT;
		
		//we have merchant
		final Merchant merchant = mt.show();
		
		//we have customer
		final Customer customer = mt.customer(cmd.getOptionValue("customer")).show();
		
		//we have the selected plan for the subscription
		final Plan plan = mt.plan(cmd.getOptionValue("plan")).show();
		
		//find or create customer in billingstack
		
		//we create a billingstack subscription
		Subscription subscription = mt.subscriptions().create(new Subscription() {{
			setCustomerId(customer.getId());
			setPlanId(plan.getId());
		}});
		
		//we create a tenant for the subscription
		Tenant tenant = new Tenant();
		tenant.setName(subscription.getId());
		tenant.setDescription(merchant.getName()+"-"+customer.getName()+"-"+plan.getName());
		tenant.setEnabled(true);
		tenant = keystone.tenants().create(tenant).execute();
		
		keystone.tenants().addUser(tenant.getId(), user.getId(), admin.getId()).execute();
		
		subscription.setResourceType("tenant");
		subscription.setResourceId(tenant.getId());
		
		mt.subscription(subscription.getId()).update(subscription);
		
	}
	
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "user", true, "user id");
		opts.addOption(null, "customer", true, "customer id");
		opts.addOption(null, "plan", true, "plan id");
		return opts;
	}

}
