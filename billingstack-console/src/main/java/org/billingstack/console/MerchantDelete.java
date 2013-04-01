package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;
import org.openstack.console.utils.ConsoleUtils;

public class MerchantDelete extends BillingStackCommand {
	
	public MerchantDelete() {
		super("merchant-delete");
	}

	@Override
	public void execute(BillingStackEndpoint bs, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			bs.merchant(args[0]).delete();
			System.out.println(new ConsoleUtils().green("OK"));
		}
	}

}
