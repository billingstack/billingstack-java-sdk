package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class CustomerDelete extends MerchantCommand {
	
	public CustomerDelete() {
		super("customer-delete");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			getMerchant(env, cmd).customer(args[0]).delete();
			System.out.println(new Console().green("OK"));
		}
	}

}
