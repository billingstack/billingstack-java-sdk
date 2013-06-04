package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.billingstack.BillingStackEndpoint;

import com.woorea.openstack.console.utils.ConsoleUtils;

public class Echo extends BillingStackCommand {
	
	public Echo() {
		super("echo");
	}

	@Override
	public void execute(BillingStackEndpoint env, final CommandLine cmd) {
		System.out.println(new ConsoleUtils().red(cmd.toString()));
	}

	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption("i", "id", true, "merchant id");
		opts.addOption("n", "name", true, "merchant name");
		opts.addOption("t", "title", true, "merchant title");
		opts.addOption("c", "currency", true, "merchant currency");
		opts.addOption("l", "language", true, "merchant language");
		return opts;
	}

	

}
