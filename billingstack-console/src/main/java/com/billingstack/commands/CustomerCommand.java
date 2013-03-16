package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.CustomerTarget;

import com.billingstack.Environment;

public abstract class CustomerCommand extends MerchantCommand {

	public CustomerCommand(String name) {
		super(name);
	}
	
	protected CustomerTarget getCustomer(Environment env, CommandLine cmd) {
		String customer = cmd.getOptionValue("customer");
		if(customer == null) {
			customer = env.getProperty(Environment.CUSTOMER);
		}
		if(customer == null) {
			throw new RuntimeException("provide the merchant id");
		}
		return getMerchant(env, cmd).customer(customer);
	}

	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "customer", true, "customer id");
		return opts;
	}
	
}

