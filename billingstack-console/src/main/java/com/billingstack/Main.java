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

import com.billingstack.commands.Bootstrap;
import com.billingstack.commands.Command;
import com.billingstack.commands.CurrencyList;
import com.billingstack.commands.CustomerList;
import com.billingstack.commands.Echo;
import com.billingstack.commands.Exit;
import com.billingstack.commands.FixedPlanItemCreate;
import com.billingstack.commands.LanguageList;
import com.billingstack.commands.MerchantCreate;
import com.billingstack.commands.MerchantList;
import com.billingstack.commands.OpenStackSourceCreate;
import com.billingstack.commands.PlanCreate;
import com.billingstack.commands.PlanList;
import com.billingstack.commands.PlanShow;
import com.billingstack.commands.ProductList;
import com.billingstack.commands.RoleList;
import com.billingstack.utils.Console;

public class Main {
	
	private static final String PROMPT = "\u001B[32mbillingstack> \u001B[0m";
	
	private static Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		commands.put("bootstrap", new Bootstrap());
		commands.put("openstack-source-create", new OpenStackSourceCreate());
		commands.put("role-list", new RoleList());
		commands.put("language-list", new LanguageList());
		commands.put("currency-list", new CurrencyList());
		commands.put("merchant-list", new MerchantList());
		commands.put("merchant-create", new MerchantCreate());
		commands.put("product-list", new ProductList());
		commands.put("plan-list", new PlanList());
		commands.put("plan-create", new PlanCreate());
		commands.put("plan-show", new PlanShow());
		commands.put("plan-item-fixed-create", new FixedPlanItemCreate());
		commands.put("customer-list", new CustomerList());
		commands.put("echo", new Echo());
		commands.put("exit", new Exit());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			CommandLineParser commandLineParser = new GnuParser();
			ConsoleReader consoleReader = new ConsoleReader();
			consoleReader.addCompleter(completer());
			Environment env = new Environment();
			String input = consoleReader.readLine(PROMPT);
			while(input != null) {
				String[] cmd = parse(input);
				if(cmd.length > 0) {
					Command command = commands.get(cmd[0]);
					if(command != null) {
						try {
							CommandLine commandLine = commandLineParser.parse(command.getOptions(), Arrays.copyOfRange(cmd, 1, cmd.length));
							command.execute(env,commandLine);
						} catch (Exception e) {
							e.printStackTrace();
							Console.red(e.getMessage());
						}
					}
				}
				input = consoleReader.readLine(PROMPT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//HelpFormatter formatter = new HelpFormatter();
			//formatter.printHelp("myapp", header, options, footer, true);
		}
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

}

