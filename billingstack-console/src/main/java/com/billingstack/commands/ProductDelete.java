package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class ProductDelete extends MerchantCommand {
	
	public ProductDelete() {
		super("product-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		getMerchant(env, cmd).product(cmd.getOptionValue("id")).delete();
	
		System.out.println(new Console().green("OK"));
	}
	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "id", true, "product id");
		return opts;
	}

}
