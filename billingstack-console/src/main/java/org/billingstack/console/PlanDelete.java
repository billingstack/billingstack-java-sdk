package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.MerchantTarget;

import com.woorea.openstack.console.utils.ConsoleUtils;

public class PlanDelete extends MerchantCommand {
	
	public PlanDelete() {
		super("plan-delete");
	}

	@Override
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.ENDPOINT.plan(args[0]).delete();
			System.out.println(new ConsoleUtils().green("OK"));
		}
	}

}
