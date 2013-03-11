package org.billingstack.openstack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.billingstack.BillingStack;

public class Main {
	
	private static Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		commands.put("openstack-create-source", new OpenStackCreateSource());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Command command = commands.get(args[0]);
		if(command != null) {
			CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse(command.getOptions(), Arrays.copyOfRange(args, 1, args.length));
			command.execute(cmd);
		}
	}
	
	static abstract class Command {

		public Options getOptions() {
			return new Options();
		}

		public abstract void execute(CommandLine cmd);
		
	}
	
	static class OpenStackCreateSource extends Command {

		public Options getOptions() {
			Options opts = super.getOptions();
			opts.addOption("m", "merchant", true, "merchant name or id");
			opts.addOption("n", "name", true, "source name");
			return opts;
		}

		public void execute(CommandLine cmd) {
			BillingStack bs = new BillingStack(Configuration.BILLINGSTACK_ENDPOINT);
			OpenStackProvider.install(bs, "merchantId", "sourceName");
			bs.close();
		}
	}

}
