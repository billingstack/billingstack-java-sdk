package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;
import com.billingstack.EnvironmentProperties;

public class CustomerEnvironment extends Command {

	public CustomerEnvironment() {
		super("customer");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		if(env.getProperty(EnvironmentProperties.MERCHANT) != null) {
			String[] args = cmd.getArgs();
			if(args.length == 1) {
				env.setProperty(EnvironmentProperties.CUSTOMER, args[0]);
			}
		} else {
			throw new RuntimeException("provide the merchant id");
		}
		
	}

}
