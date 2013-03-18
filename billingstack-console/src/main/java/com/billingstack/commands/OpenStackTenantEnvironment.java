package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;

public class OpenStackTenantEnvironment extends Command {

	public OpenStackTenantEnvironment() {
		super("openstack-tenant");
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		String[] args = cmd.getArgs();
		if(args.length == 1) {
			env.setProperty("billingstack.console.tenant", args[0]);
		} else {
			env.setProperty("billingstack.console.tenant", null);
		}
	}

}
