package com.billingstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;

import com.billingstack.commands.AccountDelete;
import com.billingstack.commands.AccountList;
import com.billingstack.commands.Bootstrap;
import com.billingstack.commands.Clean;
import com.billingstack.commands.Command;
import com.billingstack.commands.CurrencyList;
import com.billingstack.commands.CustomerCreate;
import com.billingstack.commands.CustomerDelete;
import com.billingstack.commands.CustomerEnvironment;
import com.billingstack.commands.CustomerList;
import com.billingstack.commands.Echo;
import com.billingstack.commands.EnvironmentUpdate;
import com.billingstack.commands.Exit;
import com.billingstack.commands.FixedPlanItemCreate;
import com.billingstack.commands.LanguageList;
import com.billingstack.commands.MerchantCreate;
import com.billingstack.commands.MerchantDelete;
import com.billingstack.commands.MerchantEnvironment;
import com.billingstack.commands.MerchantList;
import com.billingstack.commands.OpenStackSourceCreate;
import com.billingstack.commands.PlanCreate;
import com.billingstack.commands.PlanDelete;
import com.billingstack.commands.PlanList;
import com.billingstack.commands.PlanShow;
import com.billingstack.commands.ProductDelete;
import com.billingstack.commands.ProductList;
import com.billingstack.commands.RoleList;
import com.billingstack.commands.SubscriptionCreate;
import com.billingstack.commands.SubscriptionList;

public class Main {
	
	private static Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		add(new EnvironmentUpdate());
		add(new Bootstrap());
		add(new Clean());
		add(new OpenStackSourceCreate());
		add(new RoleList());
		add(new LanguageList());
		add(new CurrencyList());
		add(new AccountList());
		add(new AccountDelete());
		add(new MerchantList());
		add(new MerchantCreate());
		add(new MerchantDelete());
		add(new MerchantEnvironment());
		add(new ProductList());
		add(new ProductDelete());
		add(new PlanList());
		add(new PlanCreate());
		add(new PlanShow());
		add(new PlanDelete());
		add(new FixedPlanItemCreate());
		add(new CustomerList());
		add(new CustomerCreate());
		add(new CustomerDelete());
		add(new CustomerEnvironment());
		add(new SubscriptionList());
		add(new SubscriptionCreate());
		add(new Echo());
		add(new Exit());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			banner();
			HelpFormatter helpFormatter = new HelpFormatter();
			CommandLineParser commandLineParser = new GnuParser();
			ConsoleReader consoleReader = new ConsoleReader();
			consoleReader.addCompleter(completer());
			Environment env = new Environment();
			String input = consoleReader.readLine(env.getPrompt());
			while(input != null) {
				String[] cmd = parse(input);
				if(cmd.length > 0) {
					Command command = commands.get(cmd[0]);
					if(command != null) {
						try {
							CommandLine commandLine = commandLineParser.parse(command.getOptions(), Arrays.copyOfRange(cmd, 1, cmd.length));
							command.execute(env,commandLine);
						} catch (Exception e) {
							helpFormatter.printHelp(command.getName(), command.getOptions());
							e.printStackTrace();
							//Console.red(e.getMessage());
						}
					}
				}
				input = consoleReader.readLine(env.getPrompt());
			}
		} catch (Exception e) {
			e.printStackTrace();
			//HelpFormatter formatter = new HelpFormatter();
			//formatter.printHelp("myapp", header, options, footer, true);
		}
	}
	
	public static void add(Command command) {
		commands.put(command.getName(), command);
	}
	
	public static Completer completer() {

        List<Completer> c = new ArrayList<Completer>();
        for (String cmd : commands.keySet()) {

            List<Completer> cmdCompleters = new ArrayList<Completer>();
            cmdCompleters.add(new StringsCompleter(cmd));
            cmdCompleters.add(commands.get(cmd).getCompleter());
            ArgumentCompleter ac = new ArgumentCompleter(cmdCompleters);
            c.add(ac);
        }
        return new AggregateCompleter(c);
	}
	
	public static String[] parse(String input) {
        if (input == null || input.length() == 0) {
            //no command? no string
            return new String[0];
        }
        // parse with a simple finite state machine

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        StringTokenizer tok = new StringTokenizer(input, "\"\' ", true);
        Vector v = new Vector();
        StringBuffer current = new StringBuffer();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
            case inQuote:
                if ("\'".equals(nextTok)) {
                    lastTokenHasBeenQuoted = true;
                    state = normal;
                } else {
                    current.append(nextTok);
                }
                break;
            case inDoubleQuote:
                if ("\"".equals(nextTok)) {
                    lastTokenHasBeenQuoted = true;
                    state = normal;
                } else {
                    current.append(nextTok);
                }
                break;
            default:
                if ("\'".equals(nextTok)) {
                    state = inQuote;
                } else if ("\"".equals(nextTok)) {
                    state = inDoubleQuote;
                } else if (" ".equals(nextTok)) {
                    if (lastTokenHasBeenQuoted || current.length() != 0) {
                        v.addElement(current.toString());
                        current = new StringBuffer();
                    }
                } else {
                    current.append(nextTok);
                }
                lastTokenHasBeenQuoted = false;
                break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            v.addElement(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new RuntimeException("unbalanced quotes in " + input);
        }
        String[] args = new String[v.size()];
        v.copyInto(args);
        return args;
    }
	
	public static final void banner() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Welcome to BillingStack Console").append("\n\n");
		sb.append("Below you can show a set of useful commands").append("\n\n");
		sb.append("billingstack> set-property -key billingstack.endpoint -value http://home.cloudistic.me:8680/v1").append("\n");
		sb.append("billingstack> set-property -key logging -value true").append("\n");
		sb.append("billingstack> merchant-list").append("\n\n");
		System.out.println(sb);
	}

}

