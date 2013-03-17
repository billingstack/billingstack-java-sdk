package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.EnvironmentProperties;

public class EnvironmentUpdate extends Command {
	
	public EnvironmentUpdate() {
		super("set-property");
	}

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		String key = cmd.getOptionValue("key");
		String value = cmd.getOptionValue("value");
		env.setProperty(EnvironmentProperties.from(key), value);
	}

	/* (non-Javadoc)
	 * @see com.billingstack.commands.Command#getOptions()
	 */
	@Override
	public Options getOptions() {
		Options opts = super.getOptions();
		opts.addOption("key", true, "property key");
		opts.addOption("value", true, "property name");
		return opts;
	}

}