package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class PlanDelete extends MerchantCommand {
	
	public PlanDelete() {
		super("plan-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			getMerchant(env, cmd).plan(args[0]).delete();
			System.out.println(new Console().green("OK"));
		}
	}

}
