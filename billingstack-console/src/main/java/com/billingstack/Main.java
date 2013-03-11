package com.billingstack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import jline.console.ConsoleReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;

import com.billingstack.commands.Command;
import com.billingstack.commands.Echo;
import com.billingstack.commands.Exit;
import com.billingstack.commands.MerchantCreate;
import com.billingstack.commands.MerchantList;
import com.billingstack.commands.OpenStackCreateSource;
import com.billingstack.utils.ColorPrinter;

public class Main {
	
	private static Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		commands.put("openstack-create-source", new OpenStackCreateSource());
		commands.put("merchant-list", new MerchantList());
		commands.put("merchant-create", new MerchantCreate());
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
			String input = consoleReader.readLine();
			while(input != null) {
				String[] cmd = parse(input);
				if(cmd.length > 0) {
					Command command = commands.get(cmd[0]);
					if(command != null) {
						try {
							CommandLine commandLine = commandLineParser.parse(command.getOptions(), Arrays.copyOfRange(cmd, 1, cmd.length));
							Environment env = new Environment();
							command.execute(env,commandLine);
							env.getBillingStack().close();
						} catch (Exception e) {
							ColorPrinter.red(e.getMessage());
						}
					}
				}
				input = consoleReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//HelpFormatter formatter = new HelpFormatter();
			//formatter.printHelp("myapp", header, options, footer, true);
		}
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

