package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;

public class CustomerEnvironment extends Command {

	public CustomerEnvironment() {
		super("customer");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		if(env.getProperty(Environment.MERCHANT) != null) {
			String[] args = cmd.getArgs();
			if(args.length == 1) {
				env.setProperty(Environment.CUSTOMER, args[0]);
			}
		} else {
			throw new RuntimeException("provide the merchant id");
		}
		
	}

}
