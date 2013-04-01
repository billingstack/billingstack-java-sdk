package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;
import org.openstack.console.Command;
import org.openstack.console.Console;
import org.openstack.console.Environment;

public class BillingStackEnvironment extends Environment {
	
	public static final BillingStack CLIENT = new BillingStack();
	
	public BillingStackEndpoint ENDPOINT;
	
	public static final Command BILLINGSTACK = new Command("billingstack") {
		
		@Override
		public void execute(Console console, CommandLine args) {
			
			BillingStackEndpoint target = CLIENT.create(console.getProperty("billingstack.endpoint"));
			
			BillingStackEnvironment environment = new BillingStackEnvironment(console.getEnvironment(), target);
			
			environment.register(new Bootstrap());
			environment.register(new Clean());
			environment.register(new RoleList());
			environment.register(new LanguageList());
			environment.register(new CurrencyList());
			environment.register(new MerchantList());
			environment.register(new MerchantCreate());
			environment.register(new MerchantDelete());
			environment.register(MerchantEnvironment.MERCHANT);
			
			console.setEnvironment(environment);
		}
		
	};
	
	public BillingStackEnvironment(Environment parent, BillingStackEndpoint target) {
		super(parent);
		ENDPOINT = target;
	}

	/* (non-Javadoc)
	 * @see org.woorea.wsh.Environment#getPrompt()
	 */
	@Override
	public String getPrompt() {
		return "billingstack> ";
	}
	
}
