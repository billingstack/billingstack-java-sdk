package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;
import org.openstack.console.utils.ConsoleUtils;

public class ProductDelete extends MerchantCommand {
	
	public ProductDelete() {
		super("product-delete");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.ENDPOINT.product(args[0]).delete();
			System.out.println(new ConsoleUtils().green("OK"));
		}
	}

}
