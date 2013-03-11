package com.billingstack.commands;

import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;

public abstract class Command {
	
	public static final Completer NULL_COMPLETER = new NullCompleter();

	public Options getOptions() {
		return new Options();
	}
	
	public Completer getCompleter() {
		return Command.NULL_COMPLETER;
	}

	public abstract void execute(Environment env, CommandLine cmd);
	
}