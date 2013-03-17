package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.MerchantTarget;

import com.billingstack.Environment;
import com.billingstack.EnvironmentProperties;

public abstract class MerchantCommand extends Command {

	public MerchantCommand(String name) {
		super(name);
	}
	
	protected MerchantTarget getMerchant(Environment env, CommandLine cmd) {
		String merchant = cmd.getOptionValue("merchant");
		if(merchant == null) {
			merchant = env.getProperty(EnvironmentProperties.MERCHANT);
		}
		if(merchant == null) {
			throw new RuntimeException("provide the merchant id");
		}
		return env.getBillingStack().merchant(merchant);
	}

	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "merchant", true, "merchant id");
		return opts;
	}
	
}
