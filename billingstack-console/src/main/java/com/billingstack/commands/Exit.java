package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;

import com.billingstack.Environment;

public class Exit extends Command {

	@Override
	public void execute(Environment env, final CommandLine cmd) {
		System.exit(0);
	}
}
