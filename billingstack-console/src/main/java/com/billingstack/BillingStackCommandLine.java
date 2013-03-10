package com.billingstack;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.billingstack.BillingStack;

public abstract class BillingStackCommandLine {
	
	private static final Options options = new Options();
	private String[] args;

	public void execute() {
		BillingStack bs = new BillingStack("http://192.168.1.35:8080/billingstack-api");
		
		System.out.println(call(bs));
		
		bs.close();
		
		
	}

	protected abstract StringBuilder call(BillingStack bs);
	
	private void cmd() {
		
	}
	
	public static void main(String[] args) throws Exception {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		System.out.println(cmd.getArgList());
	}

}
