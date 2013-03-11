package com.billingstack.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;

public abstract class Command {

	public Options getOptions() {
		return new Options();
	}

	public abstract void execute(Environment env, CommandLine cmd);
	
}