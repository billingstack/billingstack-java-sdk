package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.MerchantTarget;

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
	public void execute(MerchantEnvironment env, CommandLine cmd) {
		//OpenStackProvider.install(getMerchant(env, cmd), cmd.getOptionValue("name"));
	}

}
