package org.billingstack.console;

import org.apache.commons.cli.CommandLine;
import org.openstack.console.Command;
import org.openstack.console.Console;

public abstract class MerchantCommand extends Command {
	
	public MerchantCommand(String name) {
		super(name);
	}

	@Override
	public void execute(Console console, CommandLine args) {
		MerchantEnvironment environment = (MerchantEnvironment) console.getEnvironment();
		execute(environment, args);
		
	}

	protected abstract void execute(MerchantEnvironment env, CommandLine args);
	
}
