package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public class Exit extends Command {

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		env.destroy();
		Console.log("Goodbye");
		System.exit(0);
	}
}
