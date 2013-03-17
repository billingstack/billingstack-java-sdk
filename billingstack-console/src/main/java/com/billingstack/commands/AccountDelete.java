package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class AccountDelete extends Command {
	
	public AccountDelete() {
		super("account-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		env.getBillingStack().account(cmd.getOptionValue("id")).delete();
	
		System.out.println(new Console().green("OK"));
	}
	
	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "id", true, "account id");
		return opts;
	}

}
