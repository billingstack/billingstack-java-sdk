package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;
import org.openstack.console.Command;
import org.openstack.console.Console;
import org.openstack.console.Environment;
import org.openstack.keystone.Keystone;
import org.openstack.keystone.api.Authenticate;
import org.openstack.keystone.model.Access;
import org.openstack.keystone.model.authentication.UsernamePassword;

public class MerchantEnvironment extends Environment {
	
	public final MerchantTarget ENDPOINT;
	
	private final Console console;
	
	public static final Command MERCHANT = new Command("merchant") {
		
		@Override
		public void execute(Console console, CommandLine args) {
			
			if(args.getArgs().length == 1) {
				
				BillingStackEnvironment parent = (BillingStackEnvironment) console.getEnvironment();
			
				MerchantEnvironment environment = new MerchantEnvironment(parent, parent.ENDPOINT.merchant(args.getArgs()[0]), console);
		
				environment.register(new ProductList());
				environment.register(new ProductDelete());
				environment.register(new PlanList());
				environment.register(new PlanCreate());
				environment.register(new PlanShow());
				environment.register(new PlanDelete());
				environment.register(new CustomerList());
				environment.register(new CustomerCreate());
				environment.register(new CustomerShow());
				environment.register(new CustomerDelete());
				environment.register(new SubscriptionList());
				environment.register(new SubscriptionCreate());
			
				console.setEnvironment(environment);
			}
		
		}
		
	};
	
	public MerchantEnvironment(Environment parent, MerchantTarget target, Console console) {
		super(parent);
		ENDPOINT = target;
		this.console = console;
	}
	
	protected Keystone keystone() {
		
		Keystone client = new Keystone(console.getProperty("keystone.endpoint"));
		
		Access access = client.tokens().authenticate(new UsernamePassword(
				console.getProperty("keystone.username"), 
				console.getProperty("keystone.password")
		)).withTenantName(console.getProperty("keystone.tenant_name")).execute();
				
		client.token(access.getToken().getId());
		return client;
	}

	/* (non-Javadoc)
	 * @see org.woorea.wsh.Environment#getPrompt()
	 */
	@Override
	public String getPrompt() {
		return "billingstack:merchant> ";
	}

}
