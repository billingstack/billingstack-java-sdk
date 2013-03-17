package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;
import com.billingstack.EnvironmentProperties;

public class MerchantEnvironment extends Command {

	public MerchantEnvironment() {
		super("merchant");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.setProperty(EnvironmentProperties.MERCHANT, args[0]);
		}
	}

}
