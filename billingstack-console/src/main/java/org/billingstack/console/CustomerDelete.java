package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.openstack.console.utils.ConsoleUtils;

public class CustomerDelete extends MerchantCommand {
	
	public CustomerDelete() {
		super("customer-delete");
	}

	@Override
	public void execute(MerchantEnvironment env, final CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.ENDPOINT.customer(args[0]).delete();
			System.out.println(new ConsoleUtils().green("OK"));
		}
	}

}
