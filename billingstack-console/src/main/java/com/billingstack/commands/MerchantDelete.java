package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class MerchantDelete extends Command {
	
	public MerchantDelete() {
		super("merchant-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.getBillingStack().merchant(args[0]).delete();
			System.out.println(new Console().green("OK"));
		}
	}

}
