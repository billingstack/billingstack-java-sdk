package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.billingstack.BillingStackEndpoint;

import com.woorea.openstack.console.Command;
import com.woorea.openstack.console.Console;
import com.woorea.openstack.keystone.Keystone;

public abstract class BillingStackCommand extends Command {
	
	public BillingStackCommand(String name) {
		super(name);
	}

	@Override
	public void execute(Console console, CommandLine args) {
		BillingStackEnvironment environment = (BillingStackEnvironment) console.getEnvironment();
		execute(environment.ENDPOINT, args);
		
	}

	protected abstract void execute(BillingStackEndpoint bs, CommandLine args);

}
