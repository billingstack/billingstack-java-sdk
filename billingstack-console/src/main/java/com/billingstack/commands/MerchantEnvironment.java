package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;

public class MerchantEnvironment extends Command {

	public MerchantEnvironment() {
		super("merchant");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.setProperty(Environment.MERCHANT, args[0]);
		}
	}

}
