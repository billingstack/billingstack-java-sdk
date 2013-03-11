package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class Echo extends Command {

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		System.out.println(Console.red(cmd.toString()));
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
