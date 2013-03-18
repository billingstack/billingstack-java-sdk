package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;

public class CustomerEnvironment extends Command {

	public CustomerEnvironment() {
		super("customer");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		if(env.getProperty("billingstack.console.merchant") != null) {
			String[] args = cmd.getArgs();
			if(args.length == 1) {
				env.setProperty("billingstack.console.customer", args[0]);
			} else {
				env.setProperty("billingstack.console.customer", null);
			}
		} else {
			throw new RuntimeException("provide the merchant id");
		}
		
	}

}
