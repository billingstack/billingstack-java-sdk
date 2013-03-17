package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.openstack.OpenStackProvider;

import com.billingstack.Environment;

public class OpenStackSourceCreate extends MerchantCommand {
	
	public OpenStackSourceCreate() {
		super("openstack-source-create");
	}

	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption(null, "name", true, "source name");
		return opts;
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		OpenStackProvider.install(getMerchant(env, cmd), cmd.getOptionValue("name"));
	}

}
