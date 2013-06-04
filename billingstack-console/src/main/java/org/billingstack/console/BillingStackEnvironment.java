package org.billingstack.console;

import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStack;
import org.billingstack.BillingStackEndpoint;

import com.woorea.openstack.console.Command;
import com.woorea.openstack.console.Console;
import com.woorea.openstack.console.Environment;

public class BillingStackEnvironment extends Environment {
	
	public BillingStackEndpoint ENDPOINT;
	
	public static final Command BILLINGSTACK = new Command("billingstack") {
		
		@Override
		public void execute(Console console, CommandLine args) {
			
			Properties properties = new Properties();
			properties.setProperty("billingstack.endpoint", console.getProperty("billingstack.endpoint"));
			
			BillingStack CLIENT = new BillingStack(properties);
			BillingStackEndpoint target = CLIENT.create();
			target.logger();
			
			BillingStackEnvironment environment = new BillingStackEnvironment(console.getEnvironment(), target);
			
			environment.register(new Bootstrap());
			environment.register(new Clean());
			environment.register(new RoleList());
			environment.register(new LanguageList());
			environment.register(new CurrencyList());
			environment.register(new PaymentGatewayProviderList());
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
