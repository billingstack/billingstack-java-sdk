package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.openstack.OpenStackProvider;

import com.billingstack.Environment;

public class OpenStackCreateSource extends Command {

	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption("m", "merchant", true, "merchant name or id");
		opts.addOption("n", "name", true, "source name");
		return opts;
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		OpenStackProvider.install(env.getBillingStack(), "merchantId", "sourceName");
	}

}
