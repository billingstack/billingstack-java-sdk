package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.openstack.OpenStackProvider;

import com.billingstack.Environment;

public class OpenStackSourceCreate extends Command {

	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption("m", "merchant", true, "merchant id");
		opts.addOption("s", "name", true, "source name");
		return opts;
	}

	@Override
	public void execute(Environment env, CommandLine cmd) {
		OpenStackProvider.install(env.getBillingStack(), cmd.getOptionValue('m'), cmd.getOptionValue("s"));
	}

}
