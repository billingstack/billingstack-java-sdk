package com.billingstack.commands;

import javax.ws.rs.WebApplicationException;

import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.billingstack.Environment;
import com.billingstack.utils.Console;

public abstract class Command {
	
	public static final Completer NULL_COMPLETER = new NullCompleter();
	
	protected String name;
	
	public Command(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public Options getOptions() {
		return new Options();
	}
	
	public Completer getCompleter() {
		return Command.NULL_COMPLETER;
	}
	
	public void call(Environment env, CommandLine cmd) {
		try {
			execute(env, cmd);
		} catch (WebApplicationException wae) {
			System.out.println(new Console().red(wae.getMessage()));
		}
	}

	public abstract void execute(Environment env, CommandLine cmd);
	
}